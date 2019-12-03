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
import java.util.Optional;
import java.util.stream.Collectors;

public class InspectorServiceImpl implements InspectorService {
    private static final Logger LOGGER = Logger.getLogger(InspectorServiceImpl.class);
    private final InspectorDao inspectorDao;
    private final InspectorMapper mapper;
    private final Validator<Inspector> validator;
    private final PasswordEncoder encoder;

    public InspectorServiceImpl(InspectorDao inspectorDao, InspectorMapper mapper, Validator<Inspector> validator, PasswordEncoder encoder) {
        this.inspectorDao = inspectorDao;
        this.mapper = mapper;
        this.validator = validator;
        this.encoder = encoder;
    }

    @Override
    public Inspector createInspector(Inspector inspector) {
        validator.validate(inspector);

        if (inspectorDao.findByLogin(inspector.getEmail()).isPresent()) {
            LOGGER.warn("Inspector with such login is already exist");
            throw new AlreadyExistUserException("Inspector with such login is already exist");
        }
        String encoded = encoder.encode(inspector.getPassword()).
                orElseThrow(() -> new InvalidEncodingException("Encode process exception"));
        Inspector withEncodedPass = new Inspector(inspector, encoded);
        inspectorDao.save(mapper.mapInspectorToInspectorEntity(withEncodedPass));
        return withEncodedPass;
    }

    @Override
    public Inspector login(String login, String password) {

        String encoded = encoder.encode(password).
                orElseThrow(() -> new InvalidEncodingException("Encode process exception"));
        Optional<InspectorEntity> inspectorEntity = inspectorDao.findByLogin(login);

        if (!inspectorEntity.isPresent()) {
            LOGGER.warn("There is no inspector with such login");
            throw new UserNotFoundException("There is no inspector with such login");
        } else {
            if (inspectorEntity.get().getPassword().equals(encoded)) {
                return mapper.mapInspectorEntityToInspector(inspectorEntity.get());
            } else {
                LOGGER.warn("Incorrect password");
                throw new UserNotFoundException("Incorrect password");
            }
        }
    }

    @Override
    public Inspector findInspectorByUserId(Long id) {
        if (Objects.isNull(id)) {
            LOGGER.warn("parameters are empty");
            throw new UserNotFoundException("Inspector was not found");
        }
        return mapper.mapInspectorEntityToInspector(inspectorDao.findByUserId(id)
                .orElseThrow(() -> new UserNotFoundException("Inspector was not found")));
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
        if (!inspectorDao.findByLogin(inspector.getEmail()).isPresent()) {
            LOGGER.warn("Inspector with such login doesnt exist");
            throw new UserNotFoundException("Inspector with such login doesnt exist");
        }
        String encoded = encoder.encode(inspector.getPassword()).
                orElseThrow(() -> new InvalidEncodingException("Encode process exception"));
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
