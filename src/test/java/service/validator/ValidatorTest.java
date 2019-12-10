package service.validator;

import domain.Inspector;
import domain.User;
import exception.InvalidRegistrationException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static data.MockData.MOCK_INSPECTOR;
import static data.MockData.MOCK_USER;

public class ValidatorTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private final Validator validator = new Validator();

    @Test
    public void validateUserShouldThrowInvalidRegistrationExceptionWithNullUser() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("User is not valid");

        validator.validateUser(null);
    }

    @Test
    public void validateUserShouldThrowInvalidRegistrationExceptionWithInvalidName() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Incorrect name");


        validator.validateUser(User.builder()
                .withName("SGH1")
                .build());
    }

    @Test
    public void validateUserShouldThrowInvalidRegistrationExceptionWithInvalidSurname() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Incorrect surname");

        validator.validateUser(User.builder()
                .withName("Name")
                .withSurname("sS1")
                .build());
    }

    @Test
    public void validateUserShouldThrowInvalidRegistrationExceptionWithInvalidEmail() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Incorrect e-mail");

        validator.validateUser(User.builder()
                .withName("Name")
                .withSurname("Surname")
                .withPatronymic("Patronymic")
                .withEmail("12")
                .build());
    }

    @Test
    public void validateUserShouldThrowInvalidRegistrationExceptionWithInvalidPassword() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Incorrect password");

        validator.validateUser(User.builder()
                .withName("Name")
                .withSurname("Surname")
                .withPatronymic("Patronymic")
                .withEmail("email@gmail.com")
                .withPassword("1")
                .build());
    }

    @Test
    public void validateUserShouldNotThrowException() {
        validator.validateUser(MOCK_USER);
    }

    @Test
    public void validateInspectorShouldThrowInvalidRegistrationExceptionWithNullUser() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Inspector is not valid");

        validator.validateInspector(null);
    }

    @Test
    public void validateInspectorShouldThrowInvalidRegistrationExceptionWithInvalidName() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Incorrect name");


        validator.validateInspector(Inspector.builder()
                .withName("SGH1")
                .build());
    }

    @Test
    public void validateInspectorShouldThrowInvalidRegistrationExceptionWithInvalidSurname() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Incorrect surname");

        validator.validateInspector(Inspector.builder()
                .withName("Name")
                .withSurname("sS1")
                .build());
    }

    @Test
    public void validateInspectorShouldThrowInvalidRegistrationExceptionWithInvalidEmail() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Incorrect e-mail");

        validator.validateInspector(Inspector.builder()
                .withName("Name")
                .withSurname("Surname")
                .withPatronymic("Patronymic")
                .withEmail("12")
                .build());
    }

    @Test
    public void validateInspectorShouldThrowInvalidRegistrationExceptionWithInvalidPassword() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Incorrect password");

        validator.validateInspector(Inspector.builder()
                .withName("Name")
                .withSurname("Surname")
                .withPatronymic("Patronymic")
                .withEmail("email@gmail.com")
                .withPassword("1")
                .build());
    }

    @Test
    public void validateInspectorShouldNotThrowException() {
        validator.validateInspector(MOCK_INSPECTOR);
    }
}