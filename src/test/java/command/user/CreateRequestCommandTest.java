package command.user;

import domain.Request;
import domain.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.RequestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static data.MockData.MOCK_USER;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateRequestCommandTest {
    private static final User USER = MOCK_USER;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private RequestService requestService;

    @InjectMocks
    private CreateRequestCommand createRequestCommand;

    @After
    public void resetMocks() {
        reset(request, session, requestService);
    }

    @Test
    public void executeShouldReturnCreatingRequestPage() {
        final String actual = createRequestCommand.execute(request);
        String expected = "/user/create-request.jsp";

        assertThat(actual, is(expected));
    }

    @Test
    public void executeShouldUpdateUser() {
        when(request.getParameter("reason")).thenReturn(anyString());
        when(session.getAttribute("user")).thenReturn(USER);
        when(request.getSession()).thenReturn(session);
        final String actual = createRequestCommand.execute(request);
        String expected = "/user/create-request.jsp";

        verify(session, times(1)).getAttribute(anyString());
        verify(requestService, times(1)).createRequest(any(Request.class));
        assertThat(actual, is(expected));
    }
}