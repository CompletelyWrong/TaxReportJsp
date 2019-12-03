package command.admin;

import command.Command;
import service.RequestService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;

public class AcceptRequest implements Command {
    private final RequestService requestService;
    private final UserService userService;

    public AcceptRequest(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long request_id = Long.valueOf(request.getParameter("request_id"));
        Long user_id = Long.valueOf(request.getParameter("user_id"));
        userService.changeInspectorForUser(userService.findById(user_id));
        requestService.deleteRequestById(request_id);

        return "/admin?command=requests";
    }
}
