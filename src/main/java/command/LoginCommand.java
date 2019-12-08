package command;

import domain.Inspector;
import domain.User;
import entity.user.Role;
import service.InspectorService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class LoginCommand {
    private final InspectorService inspectorService;
    private final UserService userService;

    public LoginCommand(InspectorService inspectorService, UserService userService) {
        this.inspectorService = inspectorService;
        this.userService = userService;
    }


    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");
        final String isWorker = request.getParameter("worker");

        if (Objects.isNull(isWorker)) {
            User user = userService.login(email, password);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("role", user.getRole());
            response.sendRedirect("/user?command=show");
        } else {
            Inspector user = inspectorService.login(email, password);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("role", user.getRole());
            if (user.getRole() == Role.ADMIN) {
                response.sendRedirect("/admin?command=users");
            } else {
                response.sendRedirect("/inspector?command=users");
            }
        }
    }
}
