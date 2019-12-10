package command.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.ActionService;

import javax.servlet.http.HttpServletRequest;

import static data.MockData.MOCK_ACTION;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowReportHistoryCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    ActionService actionService;

    @InjectMocks
    private ShowReportHistoryCommand showReportHistoryCommand;

    @Test
    public void executeShouldReturnUserPage() {
        when(request.getParameter("currentPage")).thenReturn("1");
        when(request.getParameter("reportId")).thenReturn("15");
        when(actionService.findAllForReportById(anyLong(), anyInt(), anyInt())).thenReturn(singletonList(MOCK_ACTION));

        final String actual = showReportHistoryCommand.execute(request);
        String expected = "/user/report-history.jsp";

        verify(request, times(3)).getParameter(anyString());
        verify(request, times(3)).setAttribute(anyString(), anyInt());
        verify(request).setAttribute(anyString(), anyList());
        verify(actionService).findAllForReportById(anyLong(), anyInt(), anyInt());
        assertThat(actual, is(expected));
    }
}