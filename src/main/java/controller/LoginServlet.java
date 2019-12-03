package controller;

import context.ApplicationContextInjector;
import domain.Inspector;
import domain.User;
import entity.user.Role;
import service.InspectorService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

import static java.util.Objects.isNull;

public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        if (isNull(session.getAttribute("role"))) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        final String email = request.getParameter("email");
        final String password = request.getParameter("password");
        final String isWorker = request.getParameter("worker");
        InspectorService inspectorService = injector.getInspectorService();

        if (Objects.isNull(isWorker)) {
            UserService userService = injector.getUserService();
            User user = userService.login(email, password);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("role", user.getRole());
            response.sendRedirect(request.getContextPath() + "/user");
        } else {
            Inspector user = inspectorService.login(email, password);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("role", user.getRole());

            if (user.getRole() == Role.ADMIN) {
                response.sendRedirect(request.getContextPath() + "/admin");
            } else {
                response.sendRedirect(request.getContextPath() + "/inspector");
            }
        }
    }
}
