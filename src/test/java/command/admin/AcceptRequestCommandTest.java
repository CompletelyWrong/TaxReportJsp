package command.admin;

import domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.RequestService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;

import static data.MockData.MOCK_USER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AcceptRequestCommandTest {
    private static final User USER = MOCK_USER;
    @Mock
    private HttpServletRequest request;

    @Mock
    RequestService requestService;

    @Mock
    UserService userService;

    @InjectMocks
    private AcceptRequestCommand acceptRequestCommand;

    @Test
    public void executeShouldReturnRequestsPage() {
        when(request.getParameter("requestId")).thenReturn("1");
        when(request.getParameter("userId")).thenReturn("1");
        when(userService.findById(anyLong())).thenReturn(USER);
        doNothing().when(userService).changeInspectorForUser(USER);
        doNothing().when(requestService).deleteRequestById(anyLong());

        final String actual = acceptRequestCommand.execute(request);
        String expected = "/admin?command=requests";

        verify(request, times(2)).getParameter(anyString());
        verify(requestService).deleteRequestById(anyLong());
        verify(userService).changeInspectorForUser(any(User.class));
        assertThat(actual, is(expected));
    }
}