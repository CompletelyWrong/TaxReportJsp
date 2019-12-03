package controller;

import command.Command;
import context.ApplicationContextInjector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class UserServlet extends HttpServlet {

    private final Map<String, Command> commandNameToCommand;

    public UserServlet() {
        ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        this.commandNameToCommand = injector.getUserCommands();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String command = req.getParameter("command");

        final String page = commandNameToCommand.getOrDefault(command, commandNameToCommand.get("default")).execute(req);

        req.getRequestDispatcher(page).forward(req, resp);
    }
}