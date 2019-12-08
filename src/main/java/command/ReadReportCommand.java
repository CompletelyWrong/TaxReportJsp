package command;

import domain.Action;
import domain.Inspector;
import domain.Report;
import domain.ReportStructure;
import entity.action.ActionType;
import entity.report.ReportStatus;
import service.ActionService;
import service.ReportService;
import util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

public class ReadReportCommand implements Command {
    private final ReportService reportService;
    private final ActionService actionService;
    private final JsonHelper jsonHelper;

    public ReadReportCommand(ReportService reportService, ActionService actionService, JsonHelper jsonHelper) {
        this.reportService = reportService;
        this.actionService = actionService;
        this.jsonHelper = jsonHelper;
    }

    @Override
    public String execute(HttpServletRequest request) {
        switch (getRole(request)) {
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
        Long reportId = getReport(request).getId();
        request.setAttribute("reportId", reportId);
        return "/user/view_report.jsp";
    }

    private String inspectorCommand(HttpServletRequest request) {
        Report report = getReport(request);
        Inspector inspector = getInspector(request);

        if (Objects.nonNull(request.getParameter("verdict"))) {
            ActionType verdict = ActionType.valueOf(request.getParameter("verdict"));
            Action action = Action.builder()
                    .withActionType(verdict)
                    .withDate(LocalDateTime.now())
                    .withInspector(inspector)
                    .withMessage(request.getParameter("message"))
                    .build();

            actionService.addActionForReport(action, report);
            reportService.updateReport(Report.builder(report)
                    .withStatus(getReportStatus(verdict, report))
                    .withId(report.getId())
                    .build());
        }

        return "/inspector/read-report.jsp";
    }

    private Report getReport(HttpServletRequest request) {
        Long reportId = Long.valueOf(request.getParameter("reportId"));
        Report report = reportService.findById(reportId);
        ReportStructure reportStructure = jsonHelper.readFromJson(report.getFileLink());
        request.setAttribute("report", reportStructure);
        request.setAttribute("reportId", report.getId());

        return report;
    }

    private ReportStatus getReportStatus(ActionType actionType, Report report) {
        switch (actionType) {
            case REJECT:
            case REQUEST_CHANGES:
                return ReportStatus.REJECTED;
            case ACCEPT:
                return ReportStatus.ACCEPTED;
            default:
                return report.getStatus();
        }
    }
}
