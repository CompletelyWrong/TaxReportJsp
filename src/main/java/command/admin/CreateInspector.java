package command.admin;

import command.Command;
import domain.Inspector;
import entity.user.Role;
import exception.NotEqualsPasswordException;
import service.InspectorService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class CreateInspector implements Command {
    private final InspectorService inspectorService;

    public CreateInspector(InspectorService inspectorService) {
        this.inspectorService = inspectorService;
    }

    @Override
    public String execute(HttpServletRequest request) {
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
            Inspector user = Inspector.builder()
                    .withName(name)
                    .withSurname(fullName)
                    .withPatronymic(patronymic)
                    .withEmail(email)
                    .withRole(Role.INSPECTOR)
                    .withPassword(password)
                    .build();
            inspectorService.createInspector(user);
        }

        return "/admin/create-inspector.jsp";
    }
}
