package command.user;

import command.Command;
import domain.Action;
import service.ActionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowReportHistoryCommand implements Command {
    private final ActionService actionService;

    public ShowReportHistoryCommand(ActionService actionService) {
        this.actionService = actionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = parseIntParameter(request, "currentPage");
        int recordsPerPage = parseRecordsPerPage(request, request.getParameter("recordsPerPage"));
        Long reportId = Long.valueOf(request.getParameter("reportId"));
        List<Action> actions = actionService.findAllForReportById(reportId, currentPage, recordsPerPage);

        if (actions.isEmpty()) {
            return "/user/empty-list.jsp";
        }

        request.setAttribute("actions", actions);
        int rows = actionService.getRowCountForReportById(reportId);
        paginating(request, rows, currentPage, recordsPerPage);
        request.setAttribute("reportId", reportId);

        return "/user/report-history.jsp";
    }
}
