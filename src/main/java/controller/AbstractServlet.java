package controller;

import command.Command;
import context.ApplicationContextInjector;
import exception.InvalidCommandException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class AbstractServlet extends HttpServlet{
    private static final Logger LOGGER = Logger.getLogger(AbstractServlet.class);

    private final Map<String, Command> nameToCommand;
    private final Command defaultCommand;

    AbstractServlet(String commands) {
        ApplicationContextInjector injector = ApplicationContextInjector.getInstance();

        switch (commands) {
            case "admin": {
                this.nameToCommand = injector.getAdminCommands();
                break;
            }
            case "inspector": {
                this.nameToCommand = injector.getInspectorCommands();
                break;
            }
            case "user": {
                this.nameToCommand = injector.getUserCommands();
                break;
            }
            default: {
                LOGGER.error("There are no commands with this name");
                throw new InvalidCommandException("There are no commands with this name");
            }
        }
        this.defaultCommand = injector.getDefaultCommand();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("command");

        Command currentCommand = nameToCommand.getOrDefault(action, defaultCommand);
        String page = currentCommand.execute(req);

        req.getRequestDispatcher(page).forward(req, resp);
    }
}
