package service.encoder;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PasswordEncoderTest {
    @Test
    public void encodeShouldReturnEncodedPassword() {
        String actual = passwordEncoder.encode("1111").get();
        String expected = "b59c67bf196a4758191e42f76670ceba";

        assertThat(actual, is(expected));
    }

    private final PasswordEncoder passwordEncoder = new PasswordEncoder();

}