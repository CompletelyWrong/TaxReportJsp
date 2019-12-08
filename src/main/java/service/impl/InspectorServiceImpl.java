package service.impl;

import dao.InspectorDao;
import domain.Inspector;
import entity.user.InspectorEntity;
import exception.AlreadyExistUserException;
import exception.InvalidEncodingException;
import exception.InvalidPaginationException;
import exception.UserNotFoundException;
import org.apache.log4j.Logger;
import service.InspectorService;
import service.encoder.PasswordEncoder;
import service.mapper.InspectorMapper;
import service.validator.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class InspectorServiceImpl implements InspectorService {
    private static final Logger LOGGER = Logger.getLogger(InspectorServiceImpl.class);

    private final InspectorDao inspectorDao;
    private final InspectorMapper mapper;
    private final Validator validator;
    private final PasswordEncoder encoder;

    public InspectorServiceImpl(InspectorDao inspectorDao, InspectorMapper mapper, Validator validator, PasswordEncoder encoder) {
        this.inspectorDao = inspectorDao;
        this.mapper = mapper;
        this.validator = validator;
        this.encoder = encoder;
    }

    @Override
    public Inspector createInspector(Inspector inspector) {
        validator.validateInspector(inspector);

        if (inspectorDao.findByEmail(inspector.getEmail()).isPresent()) {
            LOGGER.warn("Inspector with such login is already exist");
            throw new AlreadyExistUserException("Inspector with such login is already exist");
        }

        String encoded = encoder.encode(inspector.getPassword()).
                orElseThrow(() -> {
                    LOGGER.warn("Encode process exception");
                    return new InvalidEncodingException("Encode process exception");
                });
        Inspector withEncodedPass = new Inspector(inspector, encoded);
        inspectorDao.save(mapper.mapInspectorToInspectorEntity(withEncodedPass));

        return withEncodedPass;
    }

    @Override
    public Inspector login(String email, String password) {
        if (isNull(email) || isNull(password)) {
            LOGGER.warn("Email / password id is null");
            throw new IllegalArgumentException("Email / password id is null");
        }

        String encoded = encoder.encode(password).
                orElseThrow(() -> {
                    LOGGER.warn("Encode process exception");
                    return new InvalidEncodingException("Encode process exception");
                });

        InspectorEntity inspectorEntity = inspectorDao.findByEmail(email).orElseThrow(() -> {
            LOGGER.warn("There is no user with this email");
            return new UserNotFoundException("There is no user with this email");
        });

        if (Objects.equals(inspectorEntity.getPassword(), encoded)) {
            return mapper.mapInspectorEntityToInspector(inspectorEntity);
        }

        LOGGER.warn("Incorrect password");
        throw new UserNotFoundException("Incorrect password");
    }

    @Override
    public Inspector findInspectorByUserId(Long userId) {
        if (isNull(userId)) {
            LOGGER.warn("User id is null");
            throw new UserNotFoundException("User id is null");
        }

        return inspectorDao.findByUserId(userId)
                .map(mapper::mapInspectorEntityToInspector)
                .orElseThrow(() -> {
                    LOGGER.warn("There is no inspector with this such id");
                    return new UserNotFoundException("There is no inspector with this such id");
                });
    }

    @Override
    public Inspector findWithLessUsers() {
        return inspectorDao.findWithLessUsers()
                .map(mapper::mapInspectorEntityToInspector)
                .orElseThrow(() -> {
                    LOGGER.warn("There is no inspector in system");
                    return new UserNotFoundException("There is no inspector in system");
                });
    }

    @Override
    public Inspector findWithLessUsersExceptThisId(Long inspectorId) {
        if (isNull(inspectorId)) {
            LOGGER.warn("Inspector id is null");
            throw new UserNotFoundException("Inspector id is null");
        }

        return inspectorDao.findWithLessUsersExceptThisId(inspectorId)
                .map(mapper::mapInspectorEntityToInspector)
                .orElseThrow(() -> {
                    LOGGER.warn("There is no other inspector in system");
                    return new UserNotFoundException("There is no other inspector in system");
                });
    }

    @Override
    public List<Inspector> findAll(int rowCount, int startFrom) {
        paginationValidating(rowCount, startFrom);
        List<InspectorEntity> result = inspectorDao.findAll(rowCount, startFrom);

        return entityMapping(result);
    }

    @Override
    public List<Inspector> findAll() {
        List<InspectorEntity> result = inspectorDao.findAll();

        return entityMapping(result);
    }

    @Override
    public Integer getRowNumbers() {
        return inspectorDao.countRows();
    }

    @Override
    public void updateInfo(Inspector inspector) {
        validator.validateInspector(inspector);

        String encoded = encoder.encode(inspector.getPassword())
                .orElseThrow(() -> {
                    LOGGER.warn("Encode process exception");
                    return new InvalidEncodingException("Encode process exception");
                });

        Inspector withEncodedPass = new Inspector(inspector, encoded);
        inspectorDao.update(mapper.mapInspectorToInspectorEntity(withEncodedPass));
    }

    private void paginationValidating(int rowCount, int startFrom) {
        if (startFrom <= 0 || rowCount <= 0) {
            LOGGER.error("Invalid current page or records per page");
            throw new InvalidPaginationException("Invalid current page or records per page");
        }
    }

    private List<Inspector> entityMapping(List<InspectorEntity> result) {
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapInspectorEntityToInspector)
                .collect(Collectors.toList());
    }
}
