package command.admin;

import command.Command;
import service.RequestService;

import javax.servlet.http.HttpServletRequest;

public class RejectRequestCommand implements Command {
    private final RequestService requestService;

    public RejectRequestCommand(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long requestId = Long.valueOf(request.getParameter("requestId"));
        requestService.deleteRequestById(requestId);

        return "/admin?command=requests";
    }
}
