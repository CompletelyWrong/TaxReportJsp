package command;

import domain.Inspector;
import domain.User;
import entity.user.Role;
import service.InspectorService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

public class UpdateProfileCommand implements Command {
    private final UserService userService;
    private final InspectorService inspectorService;

    public UpdateProfileCommand(UserService userService, InspectorService inspectorService) {
        this.userService = userService;
        this.inspectorService = inspectorService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Role role = getRole(request);
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
        if (Objects.nonNull(request.getParameter("surname"))) {
            Map<String, String> profileValues = getGeneralProfileValue(request);
            User user = getUser(request);

            User updatedUser = User.builder()
                    .withName(profileValues.get("name"))
                    .withId(user.getId())
                    .withSurname(profileValues.get("surname"))
                    .withPatronymic(profileValues.get("patronymic"))
                    .withEmail(profileValues.get("email"))
                    .withIdentificationCode(user.getIdentificationCode())
                    .withRole(user.getRole())
                    .withPassword(profileValues.get("password"))
                    .build();
            userService.updateInfo(updatedUser);
            request.getSession().setAttribute("user", updatedUser);

            return "/user/u-profile.jsp";
        }

        return "/user/u-update.jsp";
    }

    private String inspectorCommand(HttpServletRequest request) {
        if (Objects.nonNull(request.getParameter("surname"))) {
            Map<String, String> profileValues = getGeneralProfileValue(request);
            Inspector inspector = (Inspector) request.getSession().getAttribute("user");

            Inspector updatedInspector = Inspector.builder()
                    .withName(profileValues.get("name"))
                    .withId(inspector.getId())
                    .withSurname(profileValues.get("surname"))
                    .withPatronymic(profileValues.get("patronymic"))
                    .withEmail(profileValues.get("email"))
                    .withRole(inspector.getRole())
                    .withPassword(profileValues.get("password"))
                    .build();
            inspectorService.updateInfo(updatedInspector);
            request.getSession().setAttribute("user", updatedInspector);

            return "/inspector/i-profile.jsp";
        }

        return "/inspector/i-update.jsp";
    }

}