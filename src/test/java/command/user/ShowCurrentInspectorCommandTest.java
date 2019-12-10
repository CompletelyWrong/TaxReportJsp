package command.user;

import domain.Inspector;
import domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.InspectorService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static data.MockData.MOCK_INSPECTOR;
import static data.MockData.MOCK_USER;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowCurrentInspectorCommandTest {
    private static final User USER = MOCK_USER;
    private static final Inspector INSPECTOR = MOCK_INSPECTOR;
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    InspectorService inspectorService;

    @InjectMocks
    private ShowCurrentInspectorCommand showCurrentInspectorCommand;

    @Test
    public void executeShouldReturnCurrentInspectorPage() {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(USER);
        when(inspectorService.findInspectorByUserId(anyLong())).thenReturn(INSPECTOR);

        final String actual = showCurrentInspectorCommand.execute(request);
        String expected = "/user/show-inspector.jsp";

        verify(request, times(1)).getSession();
        verify(session, times(1)).getAttribute(anyString());
        verify(inspectorService).findInspectorByUserId(anyLong());
        assertThat(actual, is(expected));
    }

}