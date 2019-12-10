package command.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.InspectorService;

import javax.servlet.http.HttpServletRequest;

import static data.MockData.MOCK_INSPECTOR;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ShowInspectorsCommandTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    InspectorService inspectorService;

    @InjectMocks
    private ShowInspectorsCommand showInspectorsCommand;

    @Test
    public void executeShouldReturnAdminPage() {
        when(request.getParameter("currentPage")).thenReturn("1");
        when(inspectorService.findAll(anyInt(), anyInt())).thenReturn(singletonList(MOCK_INSPECTOR));

        final String actual = showInspectorsCommand.execute(request);
        String expected = "/admin/inspectors-list.jsp";

        verify(request, times(2)).getParameter(anyString());
        verify(request, times(3)).setAttribute(anyString(), anyInt());
        verify(request).setAttribute(anyString(), anyList());
        verify(inspectorService).findAll(anyInt(), anyInt());
        assertThat(actual, is(expected));
    }
}