package service.impl;

import dao.impl.ActionDaoImpl;
import domain.Action;
import domain.Report;
import entity.action.ActionEntity;
import exception.InvalidPaginationException;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.mapper.ActionMapper;

import java.util.List;

import static data.MockData.*;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ActionServiceImplTest {
    private static final Action ACTION = MOCK_ACTION;
    private static final ActionEntity ACTION_ENTITY = MOCK_ACTION_ENTITY;
    private static final Report REPORT = MOCK_REPORT;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private ActionMapper actionMapper;

    @Mock
    private ActionDaoImpl actionDao;

    @InjectMocks
    private ActionServiceImpl actionService;

    @After
    public void resetMocks() {
        reset(actionMapper, actionDao);
    }

    @Test
    public void addActionToReportShouldThrowIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Action or report is null");
        actionService.addActionForReport(null, null);
    }

    @Test
    public void findAllForReportByIdShouldThrowIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Report id is null");
        actionService.findAllForReportById(null, 1, 1);
    }

    @Test
    public void addActionToReportShouldReturnReport() {
        when(actionMapper.mapActionToActionEntity(any(Action.class), any(Report.class)))
                .thenReturn(ACTION_ENTITY);

        actionService.addActionForReport(ACTION, REPORT);

        verify(actionDao).save(any(ActionEntity.class));
    }

    @Test
    public void findAllForReportByIdShouldReturnList() {
        when(actionMapper.mapActionEntityToAction(any(ActionEntity.class))).thenReturn(ACTION);
        when(actionDao.findAllForReportById(anyLong(), anyInt(), anyInt()))
                .thenReturn(singletonList(ACTION_ENTITY));

        List<Action> actual = actionService.findAllForReportById(1L, 1, 1);

        assertThat(actual, hasItem(ACTION));
    }

    @Test
    public void findAllForReportShouldThrowInvalidPaginationException() {
        expectedException.expect(InvalidPaginationException.class);
        expectedException.expectMessage("Invalid current page or records per page");
        actionService.findAllForReportById(1L, -1, -1);
    }
}
