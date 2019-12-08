package command.admin;

import command.Command;
import domain.Inspector;
import entity.user.Role;
import service.InspectorService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

public class CreateInspectorCommand implements Command {
    private final InspectorService inspectorService;

    public CreateInspectorCommand(InspectorService inspectorService) {
        this.inspectorService = inspectorService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        if (Objects.nonNull(request.getParameter("surname"))) {
            Map<String, String> profileValue = getGeneralProfileValue(request);

            Inspector user = Inspector.builder()
                    .withName(profileValue.get("name"))
                    .withSurname(profileValue.get("surname"))
                    .withPatronymic(profileValue.get("patronymic"))
                    .withEmail(profileValue.get("email"))
                    .withRole(Role.INSPECTOR)
                    .withPassword(profileValue.get("password"))
                    .build();
            inspectorService.createInspector(user);
        }

        return "/admin/create-inspector.jsp";
    }
}
