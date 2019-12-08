package service;

import domain.Request;

import java.util.List;

public interface RequestService {
    void createRequest(Request request);

    void deleteRequestById(Long requestId);

    List<Request> findAll(int rowCount, int startFrom);

    Integer getRowNumbers();
}
