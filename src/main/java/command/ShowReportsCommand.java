package command;

import domain.Report;
import domain.User;
import entity.user.Role;
import service.ReportService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowReportsCommand implements Command {
    private final ReportService reportService;

    public ShowReportsCommand(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Role role = getRole(request);
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
        User user = getUser(request);
        List<Report> reports = insertReportInView(request, user.getId());

        if (reports.isEmpty()) {
            return "/user/empty-list.jsp";
        }

        return "/user/report-list.jsp";
    }

    private String inspectorCommand(HttpServletRequest request) {
        long userId = Long.parseLong(request.getParameter("userId"));
        List<Report> reports = insertReportInView(request, userId);

        if (reports.isEmpty()) {
            return "/inspector/empty-list.jsp";
        }
        request.setAttribute("userId", userId);

        return "/inspector/inspector_report-list.jsp";
    }

    private List<Report> insertReportInView(HttpServletRequest request, Long userId) {
        int currentPage = parseIntParameter(request, "currentPage");
        int recordsPerPage = parseRecordsPerPage(request, request.getParameter("recordsPerPage"));
        List<Report> reports = reportService.findReportsByUser(userId, currentPage, recordsPerPage);
        int rows = reportService.getRowNumbersOfListByUser(userId);
        request.setAttribute("reports", reports);
        paginating(request, rows, currentPage, recordsPerPage);

        return reports;
    }
}