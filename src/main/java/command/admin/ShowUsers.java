package command.admin;

import command.Command;
import domain.Inspector;
import domain.User;
import entity.user.Role;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

public class ShowUsers implements Command {
    private final UserService userService;

    public ShowUsers(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        Role role = (Role) request.getSession().getAttribute("role");
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
        int currentPage = 1;
        int recordsPerPage = 10;
        if (Objects.nonNull(request.getParameter("currentPage"))) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        List<User> users = userService.findAll(currentPage, recordsPerPage);

        if (users.isEmpty()) {
            return "/admin/empty-list.jsp";
        }

        request.setAttribute("users_list", users);

        int rows = userService.getRowNumbers();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        return "/admin/users-list.jsp";
    }

    private String inspectorCommand(HttpServletRequest request) {
        int currentPage = 1;
        int recordsPerPage = 10;
        if (Objects.nonNull(request.getParameter("currentPage"))) {
            currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        Inspector inspector = (Inspector) request.getSession().getAttribute("user");
        List<User> users = userService.findAllByInspectorId(inspector.getId(), currentPage, recordsPerPage);

        if (users.isEmpty()) {
            return "/inspector/empty-list.jsp";
        }

        request.setAttribute("users_list", users);

        int rows = userService.getRowCountByInspectorId(inspector.getId());

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        return "/inspector/current-user-list.jsp";
    }
}

