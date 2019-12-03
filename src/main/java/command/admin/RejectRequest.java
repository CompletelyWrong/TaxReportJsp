package command.admin;

import command.Command;
import service.RequestService;

import javax.servlet.http.HttpServletRequest;

public class RejectRequest implements Command {
    private final RequestService requestService;

    public RejectRequest(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long id = Long.valueOf(request.getParameter("request_id"));
        requestService.deleteRequestById(id);

        return "/admin?command=requests";
    }
}
