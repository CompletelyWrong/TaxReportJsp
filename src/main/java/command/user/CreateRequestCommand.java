package command.user;

import command.Command;
import domain.Request;
import domain.User;
import service.RequestService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class CreateRequestCommand implements Command {
    private final RequestService requestService;

    public CreateRequestCommand(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (Objects.nonNull(request.getParameter("reason"))) {
            final String reason = request.getParameter("reason");
            User user = getUser(request);
            Request userRequest = new Request(user, reason);
            requestService.createRequest(userRequest);
        }

        return "/user/create-request.jsp";
    }
}
