package context;

import command.*;
import command.admin.*;
import command.user.*;
import dao.*;
import dao.impl.*;
import domain.Inspector;
import domain.User;
import service.*;
import service.encoder.PasswordEncoder;
import service.impl.*;
import service.mapper.*;
import service.validator.InspectorValidator;
import service.validator.UserValidator;
import service.validator.Validator;

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

    private static final Validator<User> USER_VALIDATOR = new UserValidator();

    private static final Validator<Inspector> INSPECTOR_VALIDATOR = new InspectorValidator();

    private static final UserMapper USER_MAPPER = new UserMapper();

    private static final ReportMapper REPORT_MAPPER = new ReportMapper();

    private static final InspectorMapper INSPECTOR_MAPPER = new InspectorMapper();

    private static final RequestMapper REQUEST_MAPPER = new RequestMapper();

    private static final ActionMapper ACTION_MAPPER = new ActionMapper();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_MAPPER, USER_VALIDATOR, PASSWORD_ENCODER, INSPECTOR_DAO);

    private static final ReportService REPORT_SERVICE = new ReportServiceImpl(REPORT_DAO, REPORT_MAPPER);

    private static final ActionService ACTION_SERVICE = new ActionServiceImpl(ACTION_DAO, ACTION_MAPPER);

    private static final RequestService REQUEST_SERVICE = new RequestServiceImpl(REQUEST_DAO, REQUEST_MAPPER);

    private static final InspectorService INSPECTOR_SERVICE = new InspectorServiceImpl(INSPECTOR_DAO, INSPECTOR_MAPPER, INSPECTOR_VALIDATOR, PASSWORD_ENCODER);

    private static final CreateInspector CREATE_INSPECTOR_COMMAND = new CreateInspector(INSPECTOR_SERVICE);

    private static final ShowInspectors SHOW_INSPECTORS_COMMAND = new ShowInspectors(INSPECTOR_SERVICE);

    private static final ShowCurrentInspector SHOW_CURRENT_INSPECTOR_COMMAND = new ShowCurrentInspector(INSPECTOR_SERVICE);

    private static final ShowRequests SHOW_REQUESTS_COMMAND = new ShowRequests(REQUEST_SERVICE);

    private static final ShowUsers SHOW_USERS_COMMAND = new ShowUsers(USER_SERVICE);

    private static final AcceptRequest ACCEPT_REQUEST_COMMAND = new AcceptRequest(REQUEST_SERVICE, USER_SERVICE);

    private static final RejectRequest REJECT_REQUEST_COMMAND = new RejectRequest(REQUEST_SERVICE);

    private static final UpdateReport UPDATE_REPORT_COMMAND = new UpdateReport(REPORT_SERVICE);

    private static final ShowReportHistory SHOW_REPORT_HISTORY_COMMAND = new ShowReportHistory(ACTION_SERVICE);

    private static final UpdateReportByFile UPDATE_REPORT_BY_FILE_COMMAND = new UpdateReportByFile(REPORT_SERVICE);

    private static final CreateRequest CREATE_REQUEST_COMMAND = new CreateRequest(REQUEST_SERVICE);

    private static final CreateReport CREATE_REPORT_COMMAND = new CreateReport(REPORT_SERVICE);

    private static final CreateReportByFile CREATE_REPORT_BY_FILE_COMMAND = new CreateReportByFile(REPORT_SERVICE);

    private static final ReadReport READ_REPORT_COMMAND = new ReadReport(REPORT_SERVICE, ACTION_SERVICE);

    private static final ShowProfile SHOW_PROFILE_COMMAND = new ShowProfile();

    private static final ShowReports SHOW_REPORTS_COMMAND = new ShowReports(REPORT_SERVICE);

    private static final UpdateProfile UPDATE_PROFILE_COMMAND = new UpdateProfile(USER_SERVICE, INSPECTOR_SERVICE);

    private static final LogOut LOGOUT_COMMAND = new LogOut();

    private static final DefaultCommand DEFAULT_COMMAND = new DefaultCommand();

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
        userCommandNameToCommand.put("add_report", CREATE_REPORT_COMMAND);
        userCommandNameToCommand.put("add_report_file", CREATE_REPORT_BY_FILE_COMMAND);
        userCommandNameToCommand.put("update_report_file", UPDATE_REPORT_BY_FILE_COMMAND);
        userCommandNameToCommand.put("add_request", CREATE_REQUEST_COMMAND);
        userCommandNameToCommand.put("update_report", UPDATE_REPORT_COMMAND);
        userCommandNameToCommand.put("report_history", SHOW_REPORT_HISTORY_COMMAND);
        userCommandNameToCommand.put("show", SHOW_REPORTS_COMMAND);
        userCommandNameToCommand.put("inspector", SHOW_CURRENT_INSPECTOR_COMMAND);
        userCommandNameToCommand.put("profile", SHOW_PROFILE_COMMAND);
        userCommandNameToCommand.put("update_profile", UPDATE_PROFILE_COMMAND);
        userCommandNameToCommand.put("report", READ_REPORT_COMMAND);
        userCommandNameToCommand.put("default", SHOW_REPORTS_COMMAND);

        return userCommandNameToCommand;
    }

    private static Map<String, Command> mapInspectorCommand() {
        Map<String, Command> inspectorCommandNameToCommand = new HashMap<>();
        inspectorCommandNameToCommand.put("logout", LOGOUT_COMMAND);
        inspectorCommandNameToCommand.put("profile", SHOW_PROFILE_COMMAND);
        inspectorCommandNameToCommand.put("update_profile", UPDATE_PROFILE_COMMAND);
        inspectorCommandNameToCommand.put("show", SHOW_REPORTS_COMMAND);
        inspectorCommandNameToCommand.put("users", SHOW_USERS_COMMAND);
        inspectorCommandNameToCommand.put("report", READ_REPORT_COMMAND);
        inspectorCommandNameToCommand.put("default", SHOW_USERS_COMMAND);

        return inspectorCommandNameToCommand;
    }

    private static Map<String, Command> mapAdminCommand() {
        Map<String, Command> adminCommandNameToCommand = new HashMap<>();
        adminCommandNameToCommand.put("logout", LOGOUT_COMMAND);
        adminCommandNameToCommand.put("profile", SHOW_PROFILE_COMMAND);
        adminCommandNameToCommand.put("accept_request", ACCEPT_REQUEST_COMMAND);
        adminCommandNameToCommand.put("reject_request", REJECT_REQUEST_COMMAND);
        adminCommandNameToCommand.put("update_profile", UPDATE_PROFILE_COMMAND);
        adminCommandNameToCommand.put("users", SHOW_USERS_COMMAND);
        adminCommandNameToCommand.put("inspectors", SHOW_INSPECTORS_COMMAND);
        adminCommandNameToCommand.put("requests", SHOW_REQUESTS_COMMAND);
        adminCommandNameToCommand.put("add_inspector", CREATE_INSPECTOR_COMMAND);
        adminCommandNameToCommand.put("default", SHOW_USERS_COMMAND);

        return adminCommandNameToCommand;
    }

    public UserService getUserService() {
        return USER_SERVICE;
    }

    public ActionService getActionService() {
        return ACTION_SERVICE;
    }

    public InspectorService getInspectorService() {
        return INSPECTOR_SERVICE;
    }

    public ReportService getReportService() {
        return REPORT_SERVICE;
    }

    public RequestService getRequestService() {
        return REQUEST_SERVICE;
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

}