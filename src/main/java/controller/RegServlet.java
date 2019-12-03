package controller;

import context.ApplicationContextInjector;
import domain.User;
import entity.user.Role;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class RegServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        final String fullName = request.getParameter("fullName");
        final String name = request.getParameter("name1");
        final String patronymic = request.getParameter("patron");
        final String email = request.getParameter("email");
        final String password = request.getParameter("password1");
        final String confirmPassword = request.getParameter("password2");
        final String roleId = request.getParameter("role_type");
        final String indCode = request.getParameter("number");
        if (!Objects.equals(password, confirmPassword)) {
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
        User user = User.builder()
                .withName(name)
                .withSurname(fullName)
                .withPatronymic(patronymic)
                .withEmail(email)
                .withIdentificationCode(Integer.parseInt(indCode))
                .withRole(Integer.parseInt(roleId) == 4 ? Role.LEGAL_TAXPAYER : Role.INDIVIDUAL_TAXPAYER)
                .withPassword(password)
                .build();
        UserService userService = injector.getUserService();
        userService.register(user);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}
