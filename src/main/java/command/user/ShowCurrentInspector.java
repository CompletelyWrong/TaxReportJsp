package command.user;

import command.Command;
import domain.Inspector;
import domain.User;
import service.InspectorService;

import javax.servlet.http.HttpServletRequest;

public class ShowCurrentInspector implements Command {
    private final InspectorService inspectorService;

    public ShowCurrentInspector(InspectorService inspectorService) {
        this.inspectorService = inspectorService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        Inspector currentInspector = inspectorService.findInspectorByUserId(user.getId());
        request.setAttribute("inspector", currentInspector);

        return "/user/show-inspector.jsp";
    }
}