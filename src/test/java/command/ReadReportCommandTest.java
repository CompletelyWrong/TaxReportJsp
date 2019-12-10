package command;

import domain.Inspector;
import domain.User;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.ReportService;
import util.JsonHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static data.MockData.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReadReportCommandTest {
    private static final User USER = MOCK_USER;
    private static final Inspector INSPECTOR = MOCK_INSPECTOR;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    private JsonHelper jsonHelper;

    @Mock
    private ReportService reportService;

    @InjectMocks
    private ReadReportCommand readReportCommand;

    @After
    public void resetMock() {
        reset(request, session, jsonHelper, reportService);
    }

    @Test
    public void executeShouldReturnUserPage() {
        when(session.getAttribute("role")).thenReturn(USER.getRole());
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("reportId")).thenReturn("1");
        when(reportService.findById(anyLong())).thenReturn(MOCK_REPORT);

        final String actual = readReportCommand.execute(request);
        String expected = "/user/view_report.jsp";

        verify(session, times(1)).getAttribute(anyString());
        assertThat(actual, is(expected));
    }

    @Test
    public void executeShouldReturnInspectorPage() {
        when(session.getAttribute("role")).thenReturn(INSPECTOR.getRole());
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("reportId")).thenReturn("1");
        when(reportService.findById(anyLong())).thenReturn(MOCK_REPORT);

        final String actual = readReportCommand.execute(request);
        String expected = "/inspector/read-report.jsp";

        verify(session, times(2)).getAttribute(anyString());
        assertThat(actual, is(expected));
    }
}