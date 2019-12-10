package service.impl;

import dao.impl.UserDaoImpl;
import domain.Inspector;
import domain.User;
import entity.user.UserEntity;
import exception.InvalidPaginationException;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.InspectorService;
import service.encoder.PasswordEncoder;
import service.mapper.UserMapper;
import service.validator.Validator;

import java.util.List;
import java.util.Optional;

import static data.MockData.*;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    private static final UserEntity USER_ENTITY = MOCK_USER_ENTITY;
    private static final User USER = MOCK_USER;
    private static final Inspector INSPECTOR = MOCK_INSPECTOR;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserDaoImpl userDao;

    @Mock
    private Validator validator;

    @Mock
    private InspectorService inspectorService;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserServiceImpl userService;

    @After
    public void resetMocks() {
        reset(userMapper, userDao, inspectorService, encoder, validator);
    }

    @Test
    public void findByIdShouldThrowIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("User id is null");
        userService.findById(null);
    }

    @Test
    public void findAllShouldThrowInvalidPaginationException() {
        expectedException.expect(InvalidPaginationException.class);
        expectedException.expectMessage("Invalid current page or records per page");
        userService.findAll(-1, -1);
    }

    @Test
    public void changeInspectorForUserShouldThrowIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("User is null");
        userService.changeInspectorForUser(null);
    }

    @Test
    public void findAllByInspectorIdShouldThrowIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Inspector id is null");
        userService.findAllByInspectorId(null, 1, 1);
    }

    @Test
    public void registerShouldReturnUser() {
        when(userMapper.mapUserToUserEntityWithInspectorId(any(User.class), anyLong()))
                .thenReturn(USER_ENTITY);
        when(userMapper.mapUserToUserEntity(any(User.class)))
                .thenReturn(USER_ENTITY);
        when(userDao.findByEmail(anyString()))
                .thenReturn(Optional.empty()).thenReturn(Optional.of(USER_ENTITY));
        when(inspectorService.findWithLessUsers())
                .thenReturn(INSPECTOR);
        when(encoder.encode(anyString()))
                .thenReturn(Optional.of("password"));

        userService.register(USER);
        verify(userDao).save(any(UserEntity.class));
    }

    @Test
    public void findByIdShouldReturnUser() {
        when(userMapper.mapUserEntityToUser(any(UserEntity.class)))
                .thenReturn(USER);

        when(userDao.findById(anyLong()))
                .thenReturn(Optional.of(USER_ENTITY));

        User actual = userService.findById(USER.getId());

        verify(userDao).findById(anyLong());
        assertThat(actual, is(USER));
    }

    @Test
    public void findAllShouldReturnList() {
        when(userMapper.mapUserEntityToUser(any(UserEntity.class)))
                .thenReturn(USER);
        when(userDao.findAll(anyInt(), anyInt()))
                .thenReturn(singletonList(USER_ENTITY));

        List<User> actual = userService.findAll(1, 10);

        assertThat(actual, hasItem(USER));
    }

    @Test
    public void updateInfoShouldReturnUser() {
        when(userMapper.mapUserToUserEntity(any(User.class)))
                .thenReturn(USER_ENTITY);
        when(userDao.findById(anyLong()))
                .thenReturn(Optional.of(USER_ENTITY));
        when(userDao.findByEmail(anyString()))
                .thenReturn(Optional.of(USER_ENTITY));
        when(encoder.encode(anyString()))
                .thenReturn(Optional.of("pass"));

        userService.updateInfo(USER);
        verify(userDao).update(any(UserEntity.class));
    }

    @Test
    public void changeInspectorForUserShouldReturnUser() {
        when(userMapper.mapUserToUserEntityWithInspectorId(any(User.class), anyLong()))
                .thenReturn(USER_ENTITY);
        when(userDao.findById(anyLong()))
                .thenReturn(Optional.of(USER_ENTITY));
        when(inspectorService.findWithLessUsersExceptThisId(anyLong()))
                .thenReturn(INSPECTOR);
        when(inspectorService.findInspectorByUserId(anyLong()))
                .thenReturn(INSPECTOR);
        userService.changeInspectorForUser(USER);

        verify(userDao).changeInspectorForUserById(any(UserEntity.class), anyLong());
    }

    @Test
    public void findAllByInspectorIdShouldReturnUser() {
        when(userMapper.mapUserEntityToUser(any(UserEntity.class)))
                .thenReturn(USER);
        when(userDao.findAllByInspectorId(anyLong(), anyInt(), anyInt()))
                .thenReturn(singletonList(USER_ENTITY));

        List<User> actual = userService.findAllByInspectorId(INSPECTOR.getId(), 1, 10);

        assertThat(actual, hasItem(USER));
    }
}