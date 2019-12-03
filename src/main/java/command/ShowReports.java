package command;

import domain.Report;
import domain.User;
import entity.user.Role;
import service.ReportService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class ShowReports implements Command {
    private final ReportService reportService;

    public ShowReports(ReportService reportService) {
        this.reportService = reportService;
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

        User user = (User) request.getSession().getAttribute("user");
        int currentPage = 1;
        int recordsPerPage = 10;
        if (Objects.nonNull(request.getParameter("currentPage"))) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        List<Report> reports = reportService.findReportsByUser(user.getId(), currentPage, recordsPerPage);

        if (reports.isEmpty()) {
            return "/user/empty-list.jsp";
        }

        request.setAttribute("reports", reports);

        int rows = reportService.getRowNumbersOfListByUser(user.getId());

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        return "/user/report-list.jsp";
    }

    private String inspectorCommand(HttpServletRequest request) {

        long user_id = Long.parseLong(request.getParameter("user_id"));
        int currentPage = 1;
        int recordsPerPage = 10;
        if (Objects.nonNull(request.getParameter("currentPage"))) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        List<Report> reports = reportService.findReportsByUser(user_id, currentPage, recordsPerPage);

        if (reports.isEmpty()) {
            return "/inspector/empty-list.jsp";
        }

        request.setAttribute("reports", reports);

        int rows = reportService.getRowNumbersOfListByUser(user_id);

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        return "/inspector/inspector_report-list.jsp";
    }
}