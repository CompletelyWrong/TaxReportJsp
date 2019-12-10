package service;

import domain.Inspector;

import java.util.List;

public interface InspectorService {
    Inspector createInspector(Inspector inspector);

    Inspector login(String login, String password);

    Inspector findInspectorByUserId(Long userId);

    Inspector findWithLessUsers();

    Inspector findWithLessUsersExceptThisId(Long inspectorId);

    List<Inspector> findAll(int rowCount, int startFrom);

    Integer getRowNumbers();

    void updateInfo(Inspector inspector);
}
