package command.user;

import command.Command;
import domain.Request;
import domain.User;
import service.RequestService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class CreateRequest implements Command {
    private final RequestService requestService;

    public CreateRequest(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (Objects.nonNull(request.getParameter("reason"))) {
            final String reason = request.getParameter("reason");
            System.out.println(reason);
            User user = (User) request.getSession().getAttribute("user");
            Request userRequest = new Request(user, reason);
            requestService.createRequest(userRequest);
        }
        return "/user/create-request.jsp";
    }
}
