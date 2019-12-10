package service.validator;

import domain.Inspector;
import domain.User;
import exception.InvalidRegistrationException;
import org.apache.log4j.Logger;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final Logger LOGGER = Logger.getLogger(Validator.class);
    private static final String NAME_REGEX = "([A-Z])([a-z]{1,40})|([А-ЯІЇЄ]([a-яіїє]{1,30}))";
    private static final String SURNAME_REGEX = "([A-Z])([a-z]{1,40})|([А-ЯІЇЄ]([a-яіїє]{1,30}))";
    private static final String PATRONYMIC_REGEX = "([A-Z])([a-z]{1,40})|([А-ЯІЇЄ]([a-яіїє]{1,30}))";
    private static final String INN_CODE_REGEX = "[\\d]{8,10}";
    private static final String EMAIL_REGEX = "(\\w{2,})@(\\w+\\.)([a-zA-Z\\d]{2,5})";
    private static final String PASSWORD_REGEX = "([^\\s][A-Za-z\\d]{5,15})";
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    private static final Pattern SURNAME_PATTERN = Pattern.compile(SURNAME_REGEX);
    private static final Pattern PATRONYMIC_PATTERN = Pattern.compile(PATRONYMIC_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static final Pattern INN_CODE_PATTERN = Pattern.compile(INN_CODE_REGEX);

    public void validateUser(User user) {
        if (Objects.isNull(user)) {
            LOGGER.warn("User is not valid");
            throw new InvalidRegistrationException("User is not valid");
        }

        validateByParam(user.getName(), NAME_PATTERN, "Incorrect name");
        validateByParam(user.getSurname(), SURNAME_PATTERN, "Incorrect surname");
        validateByParam(user.getPatronymic(), PATRONYMIC_PATTERN, "Incorrect patronymic");
        validateByParam(user.getEmail(), EMAIL_PATTERN, "Incorrect e-mail");
        validateByParam(user.getPassword(), PASSWORD_PATTERN, "Incorrect password");
        validateByParam(user.getIdentificationCode().toString(), INN_CODE_PATTERN, "Incorrect inn code");
    }

    public void validateInspector(Inspector inspector) {
        if (inspector == null) {
            LOGGER.warn("Inspector is not valid");
            throw new InvalidRegistrationException("Inspector is not valid");
        }
        validateByParam(inspector.getName(), NAME_PATTERN, "Incorrect name");
        validateByParam(inspector.getSurname(), SURNAME_PATTERN, "Incorrect surname");
        validateByParam(inspector.getPatronymic(), PATRONYMIC_PATTERN, "Incorrect patronymic");
        validateByParam(inspector.getEmail(), EMAIL_PATTERN, "Incorrect e-mail");
        validateByParam(inspector.getPassword(), PASSWORD_PATTERN, "Incorrect password");
    }

    private void validateByParam(String param, Pattern pattern, String errorMessage) {
        Matcher matcher = pattern.matcher(param);

        if (!matcher.find()) {
            LOGGER.warn(errorMessage);
            throw new InvalidRegistrationException(errorMessage);
        }
    }
}
