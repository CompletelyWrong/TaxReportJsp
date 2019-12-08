package command.admin;

import command.Command;
import domain.Request;
import service.RequestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowRequestsCommand implements Command {
    private final RequestService requestService;

    public ShowRequestsCommand(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = parseIntParameter(request, "currentPage");
        int recordsPerPage = parseRecordsPerPage(request, request.getParameter("recordsPerPage"));
        List<Request> requests = requestService.findAll(currentPage, recordsPerPage);

        if (requests.isEmpty()) {
            return "/admin/empty-list.jsp";
        }

        request.setAttribute("requests", requests);
        int rows = requestService.getRowNumbers();
        paginating(request, rows, currentPage, recordsPerPage);

        return "/admin/request-list.jsp";
    }
}
