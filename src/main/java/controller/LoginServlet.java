package controller;

import command.LoginCommand;
import context.ApplicationContextInjector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class LoginServlet extends HttpServlet {
    private final ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
    private final LoginCommand loginCommand = injector.getLoginCommand();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        if (nonNull(session) && isNull(session.getAttribute("role"))) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        loginCommand.execute(request, response);
    }
}
