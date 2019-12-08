package service.impl;

import dao.UserDao;
import domain.Inspector;
import domain.User;
import entity.user.UserEntity;
import exception.AlreadyExistUserException;
import exception.InvalidEncodingException;
import exception.InvalidPaginationException;
import exception.UserNotFoundException;
import org.apache.log4j.Logger;
import service.InspectorService;
import service.UserService;
import service.encoder.PasswordEncoder;
import service.mapper.UserMapper;
import service.validator.Validator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private final UserDao userDao;
    private final UserMapper mapper;
    private final Validator validator;
    private final PasswordEncoder encoder;
    private final InspectorService inspectorService;

    public UserServiceImpl(UserDao userDao, UserMapper mapper, Validator validator,
                           PasswordEncoder encoder, InspectorService inspectorService) {
        this.userDao = userDao;
        this.mapper = mapper;
        this.validator = validator;
        this.encoder = encoder;
        this.inspectorService = inspectorService;
    }

    @Override
    public void register(User user) {
        validator.validateUser(user);

        if (userDao.findByEmail(user.getEmail()).isPresent()) {
            LOGGER.warn("User with such login is already exist");
            throw new AlreadyExistUserException("User with such login is already exist");
        }

        String encoded = encoder.encode(user.getPassword()).
                orElseThrow(() -> new InvalidEncodingException("Encode process exception"));
        User withEncodedPass = new User(user, encoded);
        userDao.save(mapper.mapUserToUserEntity(withEncodedPass));
        setInspectorToUser(user);
    }

    @Override
    public User findById(Long userId) {
        if (isNull(userId)) {
            LOGGER.warn("User id is null");
            throw new IllegalArgumentException("User id is null");
        }

        return userDao.findById(userId)
                .map(mapper::mapUserEntityToUser)
                .orElseThrow(() -> {
                    LOGGER.warn("There is no user with this such id");
                    return new UserNotFoundException("There is no user with this such id");
                });
    }

    @Override
    public User login(String email, String password) {
        if (isNull(email) || isNull(password)) {
            throw new IllegalArgumentException("Email / password id is null");
        }

        String encoded = encoder.encode(password).
                orElseThrow(() -> {
                    LOGGER.warn("Encode process exception");
                    return new InvalidEncodingException("Encode process exception");
                });

        UserEntity userEntity = userDao.findByEmail(email).orElseThrow(() -> {
            LOGGER.warn("There is no user with this email");
            return new UserNotFoundException("There is no user with this email");
        });

        if (userEntity.getPassword().equals(encoded)) {
            return mapper.mapUserEntityToUser(userEntity);
        }

        LOGGER.warn("Incorrect password");
        throw new UserNotFoundException("Incorrect password");
    }

    @Override
    public List<User> findAll(int rowCount, int startFrom) {
        paginationValidating(rowCount, startFrom);
        List<UserEntity> result = userDao.findAll(rowCount, startFrom);

        return entityMapping(result);
    }

    @Override
    public void updateInfo(User user) {
        validator.validateUser(user);
        if (!userDao.findByEmail(user.getEmail()).isPresent()) {
            LOGGER.warn("There is no User with such email");
            throw new AlreadyExistUserException("There is no User with such email");
        }

        String encoded = encoder.encode(user.getPassword())
                .orElseThrow(() -> {
                    LOGGER.warn("Encode process exception");
                    return new InvalidEncodingException("Encode process exception");
                });
        User withEncodedPass = new User(user, encoded);
        userDao.update(mapper.mapUserToUserEntity(withEncodedPass));
    }

    @Override
    public Integer getRowNumbers() {
        return userDao.countRows();
    }

    @Override
    public void changeInspectorForUser(User user) {
        if (isNull(user)) {
            LOGGER.warn("User is null");
            throw new IllegalArgumentException("User is null");
        }

        Inspector currentInspector = inspectorService.findInspectorByUserId(user.getId());
        Long newInspectorId = inspectorService.findWithLessUsersExceptThisId(currentInspector.getId()).getId();
        userDao.changeInspectorForUserById(mapper.mapUserToUserEntityWithInspectorId(user, currentInspector.getId()),
                newInspectorId);
    }

    @Override
    public Integer getRowCountByInspectorId(Long inspectorId) {
        if (isNull(inspectorId)) {
            LOGGER.warn("Inspector id is null");
            throw new IllegalArgumentException("Inspector id is null");
        }

        return userDao.getRowCountByInspectorId(inspectorId);
    }

    @Override
    public List<User> findAllByInspectorId(Long inspectorId, int rowCount, int startFrom) {
        paginationValidating(rowCount, startFrom);
        List<UserEntity> result = userDao.findAllByInspectorId(inspectorId, rowCount, startFrom);

        return entityMapping(result);
    }

    private void setInspectorToUser(User user) {
        UserEntity withId = userDao.findByEmail(user.getEmail())
                .orElseThrow(() -> {
                    LOGGER.warn("There is no user with such id");
                    return new UserNotFoundException("There is no user with such id");
                });

        Long inspectorId = inspectorService.findWithLessUsers().getId();
        userDao.setInspectorToUser(mapper.mapUserEntityToUserEntityWithInspectorId(withId, inspectorId));
    }

    private void paginationValidating(int rowCount, int startFrom) {
        if (startFrom <= 0 || rowCount <= 0) {
            LOGGER.error("Invalid current page or records per page");
            throw new InvalidPaginationException("Invalid current page or records per page");
        }
    }

    private List<User> entityMapping(List<UserEntity> result) {
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapUserEntityToUser)
                .collect(Collectors.toList());
    }
}
