package service.impl;

import dao.RequestDao;
import domain.Request;
import entity.request.RequestEntity;
import exception.InvalidPaginationException;
import org.apache.log4j.Logger;
import service.RequestService;
import service.mapper.RequestMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class RequestServiceImpl implements RequestService {
    private static final Logger LOGGER = Logger.getLogger(RequestServiceImpl.class);

    private final RequestDao requestDao;
    private final RequestMapper mapper;

    public RequestServiceImpl(RequestDao requestDao, RequestMapper mapper) {
        this.requestDao = requestDao;
        this.mapper = mapper;
    }

    @Override
    public void createRequest(Request request) {
        if (isNull(request)) {
            LOGGER.error("Request is null");
            throw new IllegalArgumentException("Request is null");
        }

        requestDao.save(mapper.mapRequestToRequestEntity(request));
    }

    @Override
    public void deleteRequestById(Long requestId) {
        if (isNull(requestId)) {
            LOGGER.error("Request id is null");
            throw new IllegalArgumentException("Request id is null");
        }

        requestDao.deleteById(requestId);
    }

    @Override
    public List<Request> findAll(int rowCount, int startFrom) {
        paginationValidating(rowCount, startFrom);
        List<RequestEntity> result = requestDao.findAll(rowCount, startFrom);

        return entityMapping(result);
    }

    @Override
    public Integer getRowNumbers() {
        return requestDao.countRows();
    }

    private void paginationValidating(int rowCount, int startFrom) {
        if (startFrom <= 0 || rowCount <= 0) {
            LOGGER.error("Invalid current page or records per page");
            throw new InvalidPaginationException("Invalid current page or records per page");
        }
    }

    private List<Request> entityMapping(List<RequestEntity> result) {
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapRequestEntityToRequest)
                .collect(Collectors.toList());
    }
}
