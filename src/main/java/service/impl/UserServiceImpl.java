package service.impl;

import dao.InspectorDao;
import dao.UserDao;
import domain.User;
import entity.user.InspectorEntity;
import entity.user.UserEntity;
import exception.*;
import org.apache.log4j.Logger;
import service.UserService;
import service.encoder.PasswordEncoder;
import service.mapper.UserMapper;
import service.validator.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private final UserDao userDao;
    private final InspectorDao inspectorDao;
    private final UserMapper mapper;
    private final Validator<User> validator;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserDao userDao, UserMapper mapper, Validator<User> validator,
                           PasswordEncoder encoder, InspectorDao inspectorDao) {
        this.userDao = userDao;
        this.mapper = mapper;
        this.validator = validator;
        this.encoder = encoder;
        this.inspectorDao = inspectorDao;
    }

    @Override
    public void register(User user) {
        validator.validate(user);

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
    public User findById(Long id) {
        return mapper.mapUserEntityToUser(userDao.findById(id)
                .orElseThrow(() -> new UserNotFoundException("There is no inspector with such login")));
    }

    @Override
    public User login(String email, String password) {
        String encoded = encoder.encode(password).
                orElseThrow(() -> new InvalidEncodingException("Encode process exception"));
        Optional<UserEntity> userEntity = userDao.findByEmail(email);

        if (!userEntity.isPresent()) {
            LOGGER.warn("There is no inspector with such login");
            throw new UserNotFoundException("There is no inspector with such login");
        } else {
            if (userEntity.get().getPassword().equals(encoded)) {
                return mapper.mapUserEntityToUser(userEntity.get());
            } else {
                LOGGER.warn("Incorrect password");
                throw new UserNotFoundException("Incorrect password");
            }
        }
    }

    @Override
    public List<User> findAll(int rowCount, int startFrom) {
        paginationValidating(rowCount, startFrom);
        List<UserEntity> result = userDao.findAll(rowCount, startFrom);

        return entityMapping(result);
    }

    @Override
    public void updateInfo(User user) {
        if (!userDao.findByEmail(user.getEmail()).isPresent()) {
            LOGGER.warn("Inspector with such login is already exist");
            throw new AlreadyExistUserException("User with such login is already exist");
        }
        String encoded = encoder.encode(user.getPassword()).
                orElseThrow(() -> new InvalidEncodingException("Encode process exception"));
        User withEncodedPass = new User(user, encoded);
        userDao.update(mapper.mapUserToUserEntity(withEncodedPass));
    }

    @Override
    public Integer getRowNumbers() {
        return userDao.countRows();
    }

    @Override
    public void changeInspectorForUser(User user) {
        Long id = inspectorDao.findByUserId(user.getId())
                .orElseThrow(() -> new AddInspectorToUserException("This user has no Inspector"))
                .getId();
        Long chooseInspectorId = chooseInspectorId(inspectorDao.findAllExceptThisId(id));
        userDao.changeInspectorForUserById(mapper.mapUserToUserEntityWithInspectorId(user, id), chooseInspectorId);
    }

    @Override
    public Integer getRowCountByInspectorId(Long id) {
        return userDao.getRowCountByInspectorId(id);
    }

    @Override
    public List<User> findAllByInspectorId(Long id, int rowCount, int startFrom) {
        paginationValidating(rowCount, startFrom);
        List<UserEntity> result = userDao.findAllByInspectorId(id, rowCount, startFrom);

        return entityMapping(result);
    }

    private List<User> findAllByInspectorId(Long id) {
        List<UserEntity> result = userDao.findAllByInspectorId(id);

        return entityMapping(result);
    }

    private void setInspectorToUser(User user) {
        UserEntity withId = userDao.findByEmail(user.getEmail())
                .orElseThrow(() -> new AddInspectorToUserException("There is no inspectors are registered"));
        userDao.setInspectorToUser(mapper.mapUserEntityToUserEntityWithInspectorId(withId,
                chooseInspectorId(inspectorDao.findAll())));
    }

    private Long chooseInspectorId(List<InspectorEntity> entityList) {
        Map<Long, Integer> collect = entityList
                .stream()
                .collect(Collectors.toMap(InspectorEntity::getId, x -> findAllByInspectorId(x.getId()).size()));
        Integer min = Collections.min(collect.values());
        return collect.entrySet().stream()
                .filter(entry -> min.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findAny()
                .orElseThrow(() -> new AddInspectorToUserException("There is no inspectors are registered"));
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
