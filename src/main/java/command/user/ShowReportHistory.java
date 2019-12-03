package command.user;

import command.Command;
import domain.Action;
import service.ActionService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class ShowReportHistory implements Command {
    private final ActionService actionService;

    public ShowReportHistory(ActionService actionService) {
        this.actionService = actionService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = 1;
        int recordsPerPage = 10;
        Long id = Long.valueOf(request.getParameter("report_id"));
        if (Objects.nonNull(request.getParameter("currentPage"))) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        List<Action> actions = actionService.findAllForReportById(id, currentPage, recordsPerPage);
        if (actions.isEmpty()) {
            return "/user/empty-list.jsp";
        }

        request.setAttribute("actions_list", actions);

        int rows = actionService.getRowCountForReportById(id);

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        request.setAttribute("report_id", id);

        return "/user/report-history.jsp";
    }
}
