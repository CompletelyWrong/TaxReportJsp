package command.admin;

import command.Command;
import domain.Request;
import service.RequestService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class ShowRequests implements Command {
    private final RequestService requestService;

    public ShowRequests(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {

        int currentPage = 1;
        int recordsPerPage = 10;
        if (Objects.nonNull(request.getParameter("currentPage"))) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        List<Request> requests = requestService.findAll(currentPage, recordsPerPage);

        if (requests.isEmpty()) {
            return "/admin/empty-list.jsp";
        }

        request.setAttribute("requests_list", requests);

        int rows = requestService.getRowNumbers();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        return "/admin/request-list.jsp";
    }
}
