package command;

import domain.Inspector;
import domain.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static data.MockData.MOCK_INSPECTOR;
import static data.MockData.MOCK_USER;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowProfileCommandTest {
    private static final User USER = MOCK_USER;
    private static final Inspector INSPECTOR = MOCK_INSPECTOR;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private ShowProfileCommand showProfileCommand;

    @After
    public void resetMocks() {
        reset(request, session);
    }

    @Test
    public void executeShouldReturnUserPage() {
        when(session.getAttribute("role")).thenReturn(USER.getRole());
        when(request.getSession()).thenReturn(session);
        final String actual = showProfileCommand.execute(request);
        String expected = "/user/u-profile.jsp";

        verify(session, times(1)).getAttribute(anyString());
        assertThat(actual, is(expected));
    }

    @Test
    public void executeShouldReturnInspectorPage() {
        when(session.getAttribute("role")).thenReturn(INSPECTOR.getRole());
        when(request.getSession()).thenReturn(session);
        final String actual = showProfileCommand.execute(request);
        String expected = "/inspector/i-profile.jsp";

        verify(session, times(1)).getAttribute(anyString());
        assertThat(actual, is(expected));
    }
}