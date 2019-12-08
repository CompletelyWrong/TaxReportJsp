package command.admin;

import command.Command;
import domain.User;
import entity.user.Role;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowUsersCommand implements Command {
    private final UserService userService;

    public ShowUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Role role = getRole(request);
        switch (role) {
            case ADMIN:
                return adminCommand(request);
            case INSPECTOR:
                return inspectorCommand(request);
            default:
                return "/";
        }
    }

    private String adminCommand(HttpServletRequest request) {
        int currentPage = parseIntParameter(request, "currentPage");
        int recordsPerPage = parseRecordsPerPage(request, request.getParameter("recordsPerPage"));
        List<User> users = userService.findAll(currentPage, recordsPerPage);

        if (users.isEmpty()) {
            return "/admin/empty-list.jsp";
        }
        int rows = userService.getRowNumbers();
        request.setAttribute("users", users);
        paginating(request, rows, currentPage, recordsPerPage);

        return "/admin/users-list.jsp";
    }

    private String inspectorCommand(HttpServletRequest request) {
        int currentPage = parseIntParameter(request, "currentPage");
        int recordsPerPage = parseRecordsPerPage(request, request.getParameter("recordsPerPage"));
        Long inspectorId = getInspector(request).getId();
        List<User> users = userService.findAllByInspectorId(inspectorId, currentPage, recordsPerPage);

        if (users.isEmpty()) {
            return "/inspector/empty-list.jsp";
        }

        int rows = userService.getRowCountByInspectorId(inspectorId);
        request.setAttribute("users", users);
        paginating(request, rows, currentPage, recordsPerPage);

        return "/inspector/current-user-list.jsp";
    }
}

