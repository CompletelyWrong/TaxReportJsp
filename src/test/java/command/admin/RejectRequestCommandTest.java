package command.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.RequestService;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RejectRequestCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    RequestService requestService;

    @InjectMocks
    private RejectRequestCommand rejectRequestCommand;

    @Test
    public void executeShouldReturnRequestsPage() {
        when(request.getParameter("requestId")).thenReturn("1");
        doNothing().when(requestService).deleteRequestById(anyLong());

        final String actual = rejectRequestCommand.execute(request);
        String expected = "/admin?command=requests";

        verify(request, times(1)).getParameter(anyString());
        verify(requestService).deleteRequestById(anyLong());
        assertThat(actual, is(expected));
    }
}