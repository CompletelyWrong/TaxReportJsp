package command;

import domain.User;
import entity.user.Role;
import exception.NotEqualsPasswordException;
import org.apache.log4j.Logger;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class RegisterCommand {
    private static final Logger LOGGER = Logger.getLogger(RegisterCommand.class);

    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }

    public void execute(HttpServletRequest request) {
        final String surname = request.getParameter("surname");
        final String name = request.getParameter("name");
        final String patronymic = request.getParameter("patronymic");
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");
        final String confirmPassword = request.getParameter("repeatedPassword");
        final String role = request.getParameter("roleType");
        final Integer indCode = Integer.parseInt(request.getParameter("innNumber"));

        if (!Objects.equals(password, confirmPassword)) {
            LOGGER.warn("Your password is not equals");
            throw new NotEqualsPasswordException("Your password is not equals");
        }

        User user = User.builder()
                .withName(name)
                .withSurname(surname)
                .withPatronymic(patronymic)
                .withEmail(email)
                .withIdentificationCode(indCode)
                .withRole(Role.valueOf(role))
                .withPassword(password)
                .build();
        userService.register(user);
    }
}
