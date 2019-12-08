package command.admin;

import command.Command;
import service.RequestService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;

public class AcceptRequestCommand implements Command {
    private final RequestService requestService;
    private final UserService userService;

    public AcceptRequestCommand(RequestService requestService, UserService userService) {
        this.requestService = requestService;
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Long requestId = Long.valueOf(request.getParameter("requestId"));
        Long userId = Long.valueOf(request.getParameter("userId"));
        userService.changeInspectorForUser(userService.findById(userId));
        requestService.deleteRequestById(requestId);

        return "/admin?command=requests";
    }
}
