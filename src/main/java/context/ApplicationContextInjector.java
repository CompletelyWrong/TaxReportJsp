package context;

import command.*;
import command.admin.*;
import command.user.*;
import dao.*;
import dao.impl.*;
import exception.InvalidCommandException;
import service.*;
import service.encoder.PasswordEncoder;
import service.impl.*;
import service.mapper.*;
import service.validator.Validator;
import util.JsonHelper;
import util.XmlHelper;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContextInjector {
    private static final DBConnector DB_CONNECTOR = new DBConnector("DBConfig");

    private static final UserDao USER_DAO = new UserDaoImpl(DB_CONNECTOR);

    private static final InspectorDao INSPECTOR_DAO = new InspectorDaoImpl(DB_CONNECTOR);

    private static final RequestDao REQUEST_DAO = new RequestDaoImpl(DB_CONNECTOR);

    private static final ReportDao REPORT_DAO = new ReportDaoImpl(DB_CONNECTOR);

    private static final ActionDao ACTION_DAO = new ActionDaoImpl(DB_CONNECTOR);

    private static final PasswordEncoder PASSWORD_ENCODER = new PasswordEncoder();

    private static final Validator VALIDATOR = new Validator();

    private static final UserMapper USER_MAPPER = new UserMapper();

    private static final JsonHelper JSON_HELPER = new JsonHelper();

    private static final XmlHelper XML_HELPER = new XmlHelper();

    private static final ReportMapper REPORT_MAPPER = new ReportMapper();

    private static final InspectorMapper INSPECTOR_MAPPER = new InspectorMapper();

    private static final RequestMapper REQUEST_MAPPER = new RequestMapper();

    private static final ActionMapper ACTION_MAPPER = new ActionMapper();

    private static final InspectorService INSPECTOR_SERVICE = new InspectorServiceImpl(INSPECTOR_DAO, INSPECTOR_MAPPER, VALIDATOR, PASSWORD_ENCODER);

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_MAPPER, VALIDATOR, PASSWORD_ENCODER, INSPECTOR_SERVICE);

    private static final ReportService REPORT_SERVICE = new ReportServiceImpl(REPORT_DAO, REPORT_MAPPER);

    private static final ActionService ACTION_SERVICE = new ActionServiceImpl(ACTION_DAO, ACTION_MAPPER);

    private static final RequestService REQUEST_SERVICE = new RequestServiceImpl(REQUEST_DAO, REQUEST_MAPPER);

    private static final CreateInspectorCommand CREATE_INSPECTOR_COMMAND = new CreateInspectorCommand(INSPECTOR_SERVICE);

    private static final ShowInspectorsCommand SHOW_INSPECTORS_COMMAND = new ShowInspectorsCommand(INSPECTOR_SERVICE);

    private static final ShowCurrentInspectorCommand SHOW_CURRENT_INSPECTOR_COMMAND = new ShowCurrentInspectorCommand(INSPECTOR_SERVICE);

    private static final ShowRequestsCommand SHOW_REQUESTS_COMMAND = new ShowRequestsCommand(REQUEST_SERVICE);

    private static final ShowUsersCommand SHOW_USERS_COMMAND = new ShowUsersCommand(USER_SERVICE);

    private static final AcceptRequestCommand ACCEPT_REQUEST_COMMAND = new AcceptRequestCommand(REQUEST_SERVICE, USER_SERVICE);

    private static final RejectRequestCommand REJECT_REQUEST_COMMAND = new RejectRequestCommand(REQUEST_SERVICE);

    private static final UpdateReportCommand UPDATE_REPORT_COMMAND = new UpdateReportCommand(REPORT_SERVICE, JSON_HELPER);

    private static final ShowReportHistoryCommand SHOW_REPORT_HISTORY_COMMAND = new ShowReportHistoryCommand(ACTION_SERVICE);

    private static final UpdateReportByFileCommand UPDATE_REPORT_BY_FILE_COMMAND = new UpdateReportByFileCommand(REPORT_SERVICE, JSON_HELPER, XML_HELPER);

    private static final CreateRequestCommand CREATE_REQUEST_COMMAND = new CreateRequestCommand(REQUEST_SERVICE);

    private static final CreateReportCommand CREATE_REPORT_COMMAND = new CreateReportCommand(REPORT_SERVICE, JSON_HELPER);

    private static final CreateReportByFileCommand CREATE_REPORT_BY_FILE_COMMAND = new CreateReportByFileCommand(REPORT_SERVICE, JSON_HELPER, XML_HELPER);

    private static final ReadReportCommand READ_REPORT_COMMAND = new ReadReportCommand(REPORT_SERVICE, ACTION_SERVICE, JSON_HELPER);

    private static final ShowProfileCommand SHOW_PROFILE_COMMAND = new ShowProfileCommand();

    private static final ShowReportsCommand SHOW_REPORTS_COMMAND = new ShowReportsCommand(REPORT_SERVICE);

    private static final UpdateProfileCommand UPDATE_PROFILE_COMMAND = new UpdateProfileCommand(USER_SERVICE, INSPECTOR_SERVICE);

    private static final LogOutCommand LOGOUT_COMMAND = new LogOutCommand();

    private static final LoginCommand LOGIN_COMMAND = new LoginCommand(INSPECTOR_SERVICE, USER_SERVICE);

    private static final RegisterCommand REGISTER_COMMAND = new RegisterCommand(USER_SERVICE);

    private static final Command DEFAULT_COMMAND = request -> {
        throw new InvalidCommandException("There is no such command");
    };

    private static final Map<String, Command> USER_COMMAND_NAME_TO_COMMAND = mapUserCommand();

    private static final Map<String, Command> INSPECTOR_COMMAND_NAME_TO_COMMAND = mapInspectorCommand();

    private static final Map<String, Command> ADMIN_COMMAND_NAME_TO_COMMAND = mapAdminCommand();

    private static ApplicationContextInjector applicationContextInjector;

    private ApplicationContextInjector() {
    }

    public static ApplicationContextInjector getInstance() {
        if (applicationContextInjector == null) {
            synchronized (ApplicationContextInjector.class) {
                if (applicationContextInjector == null) {
                    applicationContextInjector = new ApplicationContextInjector();
                }
            }
        }
        return applicationContextInjector;
    }

    private static Map<String, Command> mapUserCommand() {
        Map<String, Command> userCommandNameToCommand = new HashMap<>();
        userCommandNameToCommand.put("logout", LOGOUT_COMMAND);
        userCommandNameToCommand.put("add-report", CREATE_REPORT_COMMAND);
        userCommandNameToCommand.put("add-report-file", CREATE_REPORT_BY_FILE_COMMAND);
        userCommandNameToCommand.put("update-report-file", UPDATE_REPORT_BY_FILE_COMMAND);
        userCommandNameToCommand.put("add-request", CREATE_REQUEST_COMMAND);
        userCommandNameToCommand.put("update-report", UPDATE_REPORT_COMMAND);
        userCommandNameToCommand.put("report-history", SHOW_REPORT_HISTORY_COMMAND);
        userCommandNameToCommand.put("show", SHOW_REPORTS_COMMAND);
        userCommandNameToCommand.put("inspector", SHOW_CURRENT_INSPECTOR_COMMAND);
        userCommandNameToCommand.put("profile", SHOW_PROFILE_COMMAND);
        userCommandNameToCommand.put("update-profile", UPDATE_PROFILE_COMMAND);
        userCommandNameToCommand.put("report", READ_REPORT_COMMAND);

        return userCommandNameToCommand;
    }

    private static Map<String, Command> mapInspectorCommand() {
        Map<String, Command> inspectorCommandNameToCommand = new HashMap<>();
        inspectorCommandNameToCommand.put("logout", LOGOUT_COMMAND);
        inspectorCommandNameToCommand.put("profile", SHOW_PROFILE_COMMAND);
        inspectorCommandNameToCommand.put("update-profile", UPDATE_PROFILE_COMMAND);
        inspectorCommandNameToCommand.put("show", SHOW_REPORTS_COMMAND);
        inspectorCommandNameToCommand.put("users", SHOW_USERS_COMMAND);
        inspectorCommandNameToCommand.put("report", READ_REPORT_COMMAND);

        return inspectorCommandNameToCommand;
    }

    private static Map<String, Command> mapAdminCommand() {
        Map<String, Command> adminCommandNameToCommand = new HashMap<>();
        adminCommandNameToCommand.put("logout", LOGOUT_COMMAND);
        adminCommandNameToCommand.put("profile", SHOW_PROFILE_COMMAND);
        adminCommandNameToCommand.put("accept-request", ACCEPT_REQUEST_COMMAND);
        adminCommandNameToCommand.put("reject-request", REJECT_REQUEST_COMMAND);
        adminCommandNameToCommand.put("update-profile", UPDATE_PROFILE_COMMAND);
        adminCommandNameToCommand.put("users", SHOW_USERS_COMMAND);
        adminCommandNameToCommand.put("inspectors", SHOW_INSPECTORS_COMMAND);
        adminCommandNameToCommand.put("requests", SHOW_REQUESTS_COMMAND);
        adminCommandNameToCommand.put("add-inspector", CREATE_INSPECTOR_COMMAND);

        return adminCommandNameToCommand;
    }

    public Command getDefaultCommand() {
        return DEFAULT_COMMAND;
    }

    public Map<String, Command> getUserCommands() {
        return USER_COMMAND_NAME_TO_COMMAND;
    }

    public Map<String, Command> getInspectorCommands() {
        return INSPECTOR_COMMAND_NAME_TO_COMMAND;
    }

    public Map<String, Command> getAdminCommands() {
        return ADMIN_COMMAND_NAME_TO_COMMAND;
    }

    public LoginCommand getLoginCommand() {
        return LOGIN_COMMAND;
    }

    public RegisterCommand getRegisterCommand() {
        return REGISTER_COMMAND;
    }
}