package data;

import domain.*;
import entity.action.ActionEntity;
import entity.action.ActionType;
import entity.report.ReportEntity;
import entity.report.ReportStatus;
import entity.request.RequestEntity;
import entity.user.InspectorEntity;
import entity.user.Role;
import entity.user.UserEntity;

import java.time.LocalDateTime;

public class MockData {
    private static final Long USER_ID = 4L;
    private static final String USER_EMAIL = "email@gmail.com";
    private static final String USER_PASSWORD = "userpass1";
    private static final String USER_NAME = "nameUser";
    private static final String USER_SURNAME = "Surname";
    private static final String USER_PATRONYMIC = "Patronymic";
    private static final Integer USER_INN_CODE = 12345678;
    private static final Role USER_ROLE = Role.INDIVIDUAL_TAXPAYER;

    private static final Long INSPECTOR_ID = 4L;
    private static final String INSPECTOR_EMAIL = "email@gmail.com";
    private static final String INSPECTOR_PASSWORD = "userpass1";
    private static final String INSPECTOR_NAME = "nameInspector";
    private static final String INSPECTOR_SURNAME = "Surname";
    private static final String INSPECTOR_PATRONYMIC = "Patronymic";
    private static final Role INSPECTOR_ROLE = Role.INSPECTOR;

    private static final Long REPORT_ID = 4L;
    private static final String REPORT_FILE_LINK = "FILE_LINK";
    private static final LocalDateTime REPORT_CREATION_TIME = LocalDateTime.of(12, 12, 12, 12, 12);
    private static final ReportStatus REPORT_STATUS = ReportStatus.REJECTED;

    private static final Long ACTION_ID = 4L;
    private static final String ACTION_MESSAGE = "Message";
    private static final LocalDateTime ACTION_CREATION_TIME = LocalDateTime.of(12, 12, 12, 12, 12);
    private static final ActionType ACTION_TYPE = ActionType.REJECT;

    private static final Long REQUEST_ID = 4L;
    private static final String REQUEST_REASON = "Reason";

    public static final Inspector MOCK_INSPECTOR = initInspector();

    public static final InspectorEntity MOCK_INSPECTOR_ENTITY = initInspectorEntity();

    public static final User MOCK_USER = initUser();

    public static final UserEntity MOCK_USER_ENTITY = initUserEntity();

    public static final Report MOCK_REPORT = initReport();

    public static final ReportEntity MOCK_REPORT_ENTITY = initReportEntity();

    public static final Action MOCK_ACTION = initAction();

    public static final ActionEntity MOCK_ACTION_ENTITY = initActionEntity();

    public static final Request MOCK_REQUEST = initRequest();

    public static final RequestEntity MOCK_REQUEST_ENTITY = initRequestEntity();

    private static User initUser() {
        return User.builder()
                .withId(USER_ID)
                .withEmail(USER_EMAIL)
                .withPassword(USER_PASSWORD)
                .withName(USER_NAME)
                .withSurname(USER_SURNAME)
                .withPatronymic(USER_PATRONYMIC)
                .withIdentificationCode(USER_INN_CODE)
                .withRole(USER_ROLE)
                .build();
    }

    private static UserEntity initUserEntity() {
        return new UserEntity.UserBuilder()
                .withId(USER_ID)
                .withEmail(USER_EMAIL)
                .withPassword(USER_PASSWORD)
                .withName(USER_NAME)
                .withSurname(USER_SURNAME)
                .withPatronymic(USER_PATRONYMIC)
                .withIdentificationCode(USER_INN_CODE)
                .withRole(USER_ROLE).build();
    }

    private static Inspector initInspector() {
        return Inspector.builder()
                .withId(INSPECTOR_ID)
                .withEmail(INSPECTOR_EMAIL)
                .withPassword(INSPECTOR_PASSWORD)
                .withName(INSPECTOR_NAME)
                .withSurname(INSPECTOR_SURNAME)
                .withPatronymic(INSPECTOR_PATRONYMIC)
                .withRole(INSPECTOR_ROLE)
                .build();
    }

    private static InspectorEntity initInspectorEntity() {
        return new InspectorEntity.InspectorBuilder().
                withId(INSPECTOR_ID)
                .withEmail(INSPECTOR_EMAIL)
                .withPassword(INSPECTOR_PASSWORD)
                .withName(INSPECTOR_NAME)
                .withSurname(INSPECTOR_NAME)
                .withPatronymic(INSPECTOR_PATRONYMIC)
                .withRole(INSPECTOR_ROLE).build();
    }

    private static Action initAction() {
        return Action.builder()
                .withId(ACTION_ID)
                .withActionType(ACTION_TYPE)
                .withDate(ACTION_CREATION_TIME)
                .withMessage(ACTION_MESSAGE)
                .withInspector(MOCK_INSPECTOR)
                .build();
    }

    private static ActionEntity initActionEntity() {
        return ActionEntity.builder().
                withId(ACTION_ID)
                .withActionType(ACTION_TYPE)
                .withDate(ACTION_CREATION_TIME)
                .withInspector(MOCK_INSPECTOR_ENTITY)
                .withMessage(ACTION_MESSAGE)
                .withReport(MOCK_REPORT_ENTITY)
                .build();
    }

    private static Report initReport() {
        return Report.builder()
                .withId(REPORT_ID)
                .withCreationDate(REPORT_CREATION_TIME)
                .withFileLink(REPORT_FILE_LINK)
                .withStatus(REPORT_STATUS)
                .withUser(MOCK_USER)
                .build();
    }

    private static ReportEntity initReportEntity() {
        return ReportEntity.builder().
                withId(REPORT_ID)
                .withCreationDate(REPORT_CREATION_TIME)
                .withFileLink(REPORT_FILE_LINK)
                .withStatus(REPORT_STATUS)
                .withUser(MOCK_USER_ENTITY)
                .build();
    }

    private static Request initRequest() {
        return new Request(REQUEST_ID, MOCK_USER, REQUEST_REASON);
    }

    private static RequestEntity initRequestEntity() {
        return new RequestEntity(REQUEST_ID, MOCK_USER_ENTITY, REQUEST_REASON);
    }
}
