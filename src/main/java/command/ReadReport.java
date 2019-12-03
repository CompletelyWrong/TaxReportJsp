package command;

import com.google.gson.Gson;
import domain.Action;
import domain.Inspector;
import domain.Report;
import domain.ReportStructure;
import entity.action.ActionType;
import entity.report.ReportStatus;
import entity.user.Role;
import service.ActionService;
import service.ReportService;

import javax.servlet.http.HttpServletRequest;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.Objects;

public class ReadReport implements Command {
    private final ReportService reportService;
    private final ActionService actionService;

    public ReadReport(ReportService reportService, ActionService actionService) {
        this.reportService = reportService;
        this.actionService = actionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Role role = (Role) request.getSession().getAttribute("role");
        switch (role) {
            case INSPECTOR:
                return inspectorCommand(request);
            case INDIVIDUAL_TAXPAYER:
            case LEGAL_TAXPAYER:
                return userCommand(request);
            default:
                return "/";
        }
    }

    private String userCommand(HttpServletRequest request) {
        getReport(request);
        return "/user/view_report.jsp";
    }

    private String inspectorCommand(HttpServletRequest request) {
        Report report = getReport(request);
        Inspector inspector = (Inspector) request.getSession().getAttribute("user");
        if (Objects.nonNull(request.getParameter("verdict"))) {
            ActionType verdict = ActionType.valueOf(request.getParameter("verdict"));
            Action action = Action.builder()
                    .withActionType(verdict)
                    .withDate(LocalDateTime.now())
                    .withInspector(inspector)
                    .withMessage(request.getParameter("message"))
                    .build();
            actionService.addActionForReport(action, report);

            reportService.updateReportById(Report.builder(report).withStatus(getReportStatus(verdict)).build(), report.getId());
            return "/inspector";
        }
        return "/inspector/read-report.jsp";
    }

    private Report getReport(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("report_id"));
        Report report = reportService.findById(id);

        try (Reader reader = new FileReader(report.getFileLink())) {
            ReportStructure structure = new Gson().fromJson(reader, ReportStructure.class);
            request.setAttribute("reportFullName", structure.getFullName());
            request.setAttribute("reportType", structure.getType());
            request.setAttribute("reportInnCode", structure.getInnCode());
            request.setAttribute("reportStartPeriod", structure.getPeriodStart());
            request.setAttribute("reportEndPeriod", structure.getPeriodEnd());
            request.setAttribute("reportClearCode", structure.getClearCode());
            request.setAttribute("reportClearValue", structure.getClearValue());
            request.setAttribute("reportIncomeCode", structure.getIncomeCode());
            request.setAttribute("reportIncomeValue", structure.getIncomeValue());
            request.setAttribute("reportOutcomeCode", structure.getOutcomeCode());
            request.setAttribute("reportOutcomeValue", structure.getOutcomeValue());
            request.setAttribute("reportPercentCode", structure.getPercentCode());
            request.setAttribute("reportPercentValue", structure.getPercentValue());
            request.setAttribute("report_id", report.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return report;
    }

    private ReportStatus getReportStatus(ActionType actionType) {
        switch (actionType) {
            case REJECT:
            case REQUEST_CHANGES:
                return ReportStatus.REJECTED;
            case ACCEPT:
                return ReportStatus.ACCEPTED;
            default:
                return ReportStatus.NEW;
        }
    }
}
