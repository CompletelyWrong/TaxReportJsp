package command.admin;

import data.MockData;
import domain.Inspector;
import entity.user.Role;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static data.MockData.MOCK_INSPECTOR;
import static data.MockData.MOCK_USER;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowUsersCommandTest {
    private static final Inspector INSPECTOR = MOCK_INSPECTOR;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Mock
    UserService userService;

    @InjectMocks
    private ShowUsersCommand showUsersCommand;

    @After
    public void resetMocks() {
        reset(request, session, userService);
    }

    @Test
    public void executeShouldReturnAdminPage() {
        when(request.getParameter("currentPage")).thenReturn("1");
        when(request.getParameter("rowCount")).thenReturn("15");
        when(session.getAttribute("role")).thenReturn(Role.ADMIN);
        when(request.getSession()).thenReturn(session);
        when(userService.findAll(anyInt(), anyInt())).thenReturn(singletonList(MockData.MOCK_USER));

        final String actual = showUsersCommand.execute(request);
        String expected = "/admin/users-list.jsp";

        verify(request, times(2)).getParameter(anyString());
        verify(request, times(3)).setAttribute(anyString(), anyInt());
        verify(request).setAttribute(anyString(), anyList());
        verify(userService).findAll(anyInt(), anyInt());
        assertThat(actual, is(expected));
    }

    @Test
    public void executeShouldReturnInspectorPage() {
        when(request.getParameter("currentPage")).thenReturn("1");
        when(request.getParameter("rowCount")).thenReturn("15");
        when(session.getAttribute("role")).thenReturn(INSPECTOR.getRole());
        when(session.getAttribute("user")).thenReturn(INSPECTOR);
        when(request.getSession()).thenReturn(session);
        when(userService.findAllByInspectorId(anyLong(), anyInt(), anyInt())).thenReturn(singletonList(MOCK_USER));

        final String actual = showUsersCommand.execute(request);
        String expected = "/inspector/current-user-list.jsp";

        verify(request, times(2)).getParameter(anyString());
        verify(request, times(3)).setAttribute(anyString(), anyInt());
        verify(request).setAttribute(anyString(), anyList());
        verify(userService).findAllByInspectorId(anyLong(), anyInt(), anyInt());
        assertThat(actual, is(expected));
    }
}