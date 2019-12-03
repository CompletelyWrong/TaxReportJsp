package service.validator;

import domain.Inspector;
import exception.InvalidRegistrationException;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InspectorValidator implements Validator<Inspector> {
    private static final Logger LOGGER = Logger.getLogger(UserValidator.class);

    private static final String NAME_REGEX = "([A-Z])([a-z]{1,12})|([А-Я]([a-я]{1,12}))";
    private static final String SURNAME_REGEX = "([A-Z])([a-z]{1,12})|([А-Я]([a-я]{1,12}))";
    private static final String PATRONYMIC_REGEX = "([A-Z])([a-z]{1,12})|([А-Я]([a-я]{1,12}))";
    private static final String EMAIL_REGEX = "(\\w{2,})@(\\w+\\.)([a-z]{2,5})";
    private static final String PASSWORD_REGEX = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,}";
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    private static final Pattern SURNAME_PATTERN = Pattern.compile(SURNAME_REGEX);
    private static final Pattern PATRONYMIC_PATTERN = Pattern.compile(PATRONYMIC_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);

    @Override
    public void validate(Inspector user) {
        if (user == null) {
            LOGGER.warn("User is not valid");
            throw new InvalidRegistrationException("User is not valid");
        }
        validateByParam(user.getName(), NAME_PATTERN, "Incorrect name");
        validateByParam(user.getSurname(), SURNAME_PATTERN, "Incorrect surname");
        validateByParam(user.getPatronymic(), PATRONYMIC_PATTERN, "Incorrect patronymic");
        validateByParam(user.getEmail(), EMAIL_PATTERN, "Incorrect e-mail");
        validateByParam(user.getPassword(), PASSWORD_PATTERN, "Incorrect password");
    }

    private void validateByParam(String param, Pattern pattern, String errorMessage) {
        Matcher matcher = pattern.matcher(param);

        if (!matcher.find()) {
            LOGGER.warn(errorMessage);
            throw new InvalidRegistrationException(errorMessage);
        }
    }
}
