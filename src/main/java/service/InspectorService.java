package service;

import domain.Inspector;

import java.util.List;

public interface InspectorService {
    Inspector createInspector(Inspector inspector);

    Inspector login(String login, String password);

    Inspector findInspectorByUserId(Long id);

    List<Inspector> findAll(int rowCount, int startFrom);

    List<Inspector> findAll();

    Integer getRowNumbers();

    void updateInfo(Inspector inspector);
}
