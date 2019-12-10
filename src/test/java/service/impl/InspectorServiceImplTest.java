package service.impl;

import dao.impl.InspectorDaoImpl;
import domain.Inspector;
import domain.User;
import entity.user.InspectorEntity;
import exception.InvalidPaginationException;
import exception.InvalidRegistrationException;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.encoder.PasswordEncoder;
import service.mapper.InspectorMapper;
import service.validator.Validator;

import java.util.List;
import java.util.Optional;

import static data.MockData.*;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InspectorServiceImplTest {
    private static final Inspector INSPECTOR = MOCK_INSPECTOR;
    private static final InspectorEntity INSPECTOR_ENTITY = MOCK_INSPECTOR_ENTITY;
    private static final User USER = MOCK_USER;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private InspectorMapper inspectorMapper;

    @Mock
    private InspectorDaoImpl inspectorDao;

    @Mock
    private Validator validator;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private InspectorServiceImpl inspectorService;

    @After
    public void resetMocks() {
        reset(inspectorMapper, inspectorDao, encoder, validator);
    }

    @Test
    public void createInspectorShouldInvalidRegistrationException() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Inspector is null");
        doThrow(new InvalidRegistrationException("Inspector is null")).when(validator).validateInspector(null);
        inspectorService.createInspector(null);
    }

    @Test
    public void updateInfoShouldThrowInvalidRegistrationException() {
        expectedException.expect(InvalidRegistrationException.class);
        expectedException.expectMessage("Inspector is null");
        doThrow(new InvalidRegistrationException("Inspector is null")).when(validator).validateInspector(null);
        inspectorService.updateInfo(null);
    }

    @Test
    public void findByUserIdShouldThrowIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("User id is null");
        inspectorService.findInspectorByUserId(null);
    }

    @Test
    public void findAllShouldThrowInvalidPaginationException() {
        expectedException.expect(InvalidPaginationException.class);
        expectedException.expectMessage("Invalid current page or records per page");
        inspectorService.findAll(-1, -1);
    }

    @Test
    public void findWithLessUsersExceptThisIdShouldThrowIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Inspector id is null");
        inspectorService.findWithLessUsersExceptThisId(null);
    }

    @Test
    public void createInspectorShouldReturnInspector() {
        when(inspectorMapper.mapInspectorToInspectorEntity(any(Inspector.class)))
                .thenReturn(INSPECTOR_ENTITY);
        when(encoder.encode(anyString()))
                .thenReturn(Optional.of("userpass1"));
        Inspector actual = inspectorService.createInspector(INSPECTOR);

        verify(inspectorDao).save(any(InspectorEntity.class));
        assertThat(actual, is(INSPECTOR));
    }

    @Test
    public void updateInfoShouldReturnInspector() {
        when(inspectorMapper.mapInspectorToInspectorEntity(any(Inspector.class)))
                .thenReturn(INSPECTOR_ENTITY);
        when(inspectorMapper.mapInspectorEntityToInspector(any(InspectorEntity.class)))
                .thenReturn(INSPECTOR);
        when(inspectorDao.findById(anyLong()))
                .thenReturn(Optional.of(INSPECTOR_ENTITY));
        when(encoder.encode(anyString()))
                .thenReturn(Optional.of("password"));

        inspectorService.updateInfo(INSPECTOR);

        verify(inspectorDao).update(any(InspectorEntity.class));
    }

    @Test
    public void findByUserIdShouldReturnInspector() {
        when(inspectorMapper.mapInspectorEntityToInspector(any(InspectorEntity.class)))
                .thenReturn(INSPECTOR);

        when(inspectorDao.findByUserId(anyLong()))
                .thenReturn(Optional.of(INSPECTOR_ENTITY));

        Inspector actual = inspectorService.findInspectorByUserId(USER.getId());

        verify(inspectorDao).findByUserId(anyLong());
        assertThat(actual, is(INSPECTOR));
    }

    @Test
    public void findAllShouldReturnList() {
        when(inspectorMapper.mapInspectorEntityToInspector(any(InspectorEntity.class)))
                .thenReturn(INSPECTOR);
        when(inspectorDao.findAll(anyInt(), anyInt()))
                .thenReturn(singletonList(INSPECTOR_ENTITY));

        List<Inspector> actual = inspectorService.findAll(1, 10);

        assertThat(actual, hasItem(INSPECTOR));
    }

    @Test
    public void findWithLessUsersExceptThisIdShouldReturnInspector() {
        when(inspectorMapper.mapInspectorEntityToInspector(any(InspectorEntity.class)))
                .thenReturn(INSPECTOR);

        when(inspectorDao.findWithLessUsersExceptThisId(anyLong()))
                .thenReturn(Optional.of(INSPECTOR_ENTITY));

        Inspector actual = inspectorService.findWithLessUsersExceptThisId(INSPECTOR.getId());

        verify(inspectorDao).findWithLessUsersExceptThisId(anyLong());
        assertThat(actual, is(INSPECTOR));
    }

    @Test
    public void findWithLessUsersReturnInspector() {
        when(inspectorMapper.mapInspectorEntityToInspector(any(InspectorEntity.class)))
                .thenReturn(INSPECTOR);

        when(inspectorDao.findWithLessUsers())
                .thenReturn(Optional.of(INSPECTOR_ENTITY));

        Inspector actual = inspectorService.findWithLessUsers();

        verify(inspectorDao).findWithLessUsers();
        assertThat(actual, is(INSPECTOR));
    }
}