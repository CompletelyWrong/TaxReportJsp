package command;

import data.MockData;
import domain.Inspector;
import domain.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.ReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static data.MockData.MOCK_INSPECTOR;
import static data.MockData.MOCK_USER;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowReportsCommandTest {
    private static final User USER = MOCK_USER;
    private static final Inspector INSPECTOR = MOCK_INSPECTOR;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    ReportService reportService;

    @InjectMocks
    private ShowReportsCommand showReportsCommand;

    @After
    public void resetMocks() {
        reset(request, session, reportService);
    }

    @Test
    public void executeShouldReturnUserPage() {
        when(request.getParameter("currentPage")).thenReturn("1");
        when(request.getParameter("rowCount")).thenReturn("15");
        when(session.getAttribute("role")).thenReturn(USER.getRole());
        when(session.getAttribute("user")).thenReturn(USER);
        when(request.getSession()).thenReturn(session);
        when(reportService.findReportsByUser(anyLong(), anyInt(), anyInt())).thenReturn(singletonList(MockData.MOCK_REPORT));

        final String actual = showReportsCommand.execute(request);
        String expected = "/user/report-list.jsp";

        verify(request, times(2)).getParameter(anyString());
        verify(request, times(3)).setAttribute(anyString(), anyInt());
        verify(request).setAttribute(anyString(), anyList());
        verify(reportService).findReportsByUser(anyLong(), anyInt(), anyInt());
        assertThat(actual, is(expected));
    }

    @Test
    public void executeShouldReturnInspectorPage() {
        when(request.getParameter("currentPage")).thenReturn("1");
        when(request.getParameter("rowCount")).thenReturn("15");
        when(request.getParameter("userId")).thenReturn("1");
        when(session.getAttribute("role")).thenReturn(INSPECTOR.getRole());
        when(session.getAttribute("user")).thenReturn(INSPECTOR);
        when(request.getSession()).thenReturn(session);
        when(reportService.findReportsByUser(anyLong(), anyInt(), anyInt())).thenReturn(singletonList(MockData.MOCK_REPORT));

        final String actual = showReportsCommand.execute(request);
        String expected = "/inspector/inspector_report-list.jsp";

        verify(request, times(3)).getParameter(anyString());
        verify(request, times(3)).setAttribute(anyString(), anyInt());
        verify(request).setAttribute(anyString(), anyList());
        verify(reportService).findReportsByUser(anyLong(), anyInt(), anyInt());
        assertThat(actual, is(expected));
    }
}