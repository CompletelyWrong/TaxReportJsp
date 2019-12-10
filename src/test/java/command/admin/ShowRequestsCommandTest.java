package command.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.RequestService;

import javax.servlet.http.HttpServletRequest;

import static data.MockData.MOCK_REQUEST;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowRequestsCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    RequestService requestService;

    @InjectMocks
    private ShowRequestsCommand showRequestsCommand;

    @Test
    public void executeShouldReturnAdminPage() {
        when(request.getParameter("currentPage")).thenReturn("1");
        when(requestService.findAll(anyInt(), anyInt())).thenReturn(singletonList(MOCK_REQUEST));

        final String actual = showRequestsCommand.execute(request);
        String expected = "/admin/request-list.jsp";

        verify(request, times(2)).getParameter(anyString());
        verify(request, times(3)).setAttribute(anyString(), anyInt());
        verify(request).setAttribute(anyString(), anyList());
        verify(requestService).findAll(anyInt(), anyInt());
        assertThat(actual, is(expected));
    }
}