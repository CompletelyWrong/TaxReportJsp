package service.impl;

import dao.impl.RequestDaoImpl;
import domain.Request;
import entity.request.RequestEntity;
import exception.InvalidPaginationException;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.mapper.RequestMapper;

import java.util.List;

import static data.MockData.MOCK_REQUEST;
import static data.MockData.MOCK_REQUEST_ENTITY;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReportServiceImplTest {
    private static final Request REQUEST = MOCK_REQUEST;
    private static final RequestEntity REQUEST_ENTITY = MOCK_REQUEST_ENTITY;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private RequestMapper requestMapper;

    @Mock
    private RequestDaoImpl requestDao;

    @InjectMocks
    private RequestServiceImpl requestService;

    @After
    public void resetMocks() {
        reset(requestMapper, requestDao);
    }

    @Test
    public void createRequestShouldThrowIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Request is null");
        requestService.createRequest(null);
    }

    @Test
    public void deleteRequestByIdShouldThrowIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Request id is null");
        requestService.deleteRequestById(null);
    }

    @Test
    public void findAllShouldThrowInvalidPaginationException() {
        expectedException.expect(InvalidPaginationException.class);
        expectedException.expectMessage("Invalid current page or records per page");
        requestService.findAll(-1, 1);
    }

    @Test
    public void createRequestShouldReturnRequest() {
        when(requestMapper.mapRequestToRequestEntity(any(Request.class)))
                .thenReturn(REQUEST_ENTITY);

        requestService.createRequest(REQUEST);

        verify(requestDao).save(any(RequestEntity.class));
    }

    @Test
    public void deleteRequestByIdShouldReturnRequest() {
        when(requestMapper.mapRequestToRequestEntity(any(Request.class)))
                .thenReturn(REQUEST_ENTITY);

        requestService.deleteRequestById(REQUEST.getId());

        verify(requestDao).deleteById(anyLong());

    }

    @Test
    public void findAllShouldReturnList() {
        when(requestMapper.mapRequestEntityToRequest(any(RequestEntity.class))).thenReturn(REQUEST);
        when(requestDao.findAll(anyInt(), anyInt()))
                .thenReturn(singletonList(REQUEST_ENTITY));

        List<Request> actual = requestService.findAll(1, 10);

        assertThat(actual, hasItem(REQUEST));
    }
}