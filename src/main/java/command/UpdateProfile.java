package command;

import domain.Inspector;
import domain.User;
import entity.user.Role;
import exception.NotEqualsPasswordException;
import service.InspectorService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class UpdateProfile implements Command {
    private final UserService userService;
    private final InspectorService inspectorService;

    public UpdateProfile(UserService userService, InspectorService inspectorService) {
        this.userService = userService;
        this.inspectorService = inspectorService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Role role = (Role) request.getSession().getAttribute("role");
        switch (role) {
            case INSPECTOR:
                return inspectorCommand(request);
            case INDIVIDUAL_TAXPAYER:
            case LEGAL_TAXPAYER:
                return userCommand(request);

            default:
                return "/login";
        }
    }

    private String userCommand(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (Objects.nonNull(request.getParameter("fullName"))) {
            final String fullName = request.getParameter("fullName");
            final String name = request.getParameter("name1");
            final String patronymic = request.getParameter("patron");
            final String email = request.getParameter("email");
            final String password = request.getParameter("password1");
            final String confirmPassword = request.getParameter("password2");
            final String indCode = request.getParameter("number");
            if (!Objects.equals(password, confirmPassword)) {
                throw new NotEqualsPasswordException("Your password is not equals");
            }
            User updatedUser = User.builder()
                    .withName(name)
                    .withId(user.getId())
                    .withSurname(fullName)
                    .withPatronymic(patronymic)
                    .withEmail(email)
                    .withIdentificationCode(Integer.parseInt(indCode))
                    .withRole(user.getRole())
                    .withPassword(password)
                    .build();
            userService.updateInfo(updatedUser);
            request.getSession().setAttribute("user", updatedUser);
        }
        return "/user/u-update.jsp";
    }

    private String inspectorCommand(HttpServletRequest request) {
        Inspector user = (Inspector) request.getSession().getAttribute("user");
        if (Objects.nonNull(request.getParameter("fullName"))) {
            final String fullName = request.getParameter("fullName");
            final String name = request.getParameter("name1");
            final String patronymic = request.getParameter("patron");
            final String email = request.getParameter("email");
            final String password = request.getParameter("password1");
            final String confirmPassword = request.getParameter("password2");
            if (!Objects.equals(password, confirmPassword)) {
                throw new NotEqualsPasswordException("Your password is not equals");
            }
            Inspector updatedUser = Inspector.builder()
                    .withName(name)
                    .withId(user.getId())
                    .withSurname(fullName)
                    .withPatronymic(patronymic)
                    .withEmail(email)
                    .withRole(user.getRole())
                    .withPassword(password)
                    .build();
            inspectorService.updateInfo(updatedUser);
            request.getSession().setAttribute("user", updatedUser);
        }
        return "/inspector/i-update.jsp";
    }
}