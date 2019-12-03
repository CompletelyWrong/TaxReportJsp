package service;

import domain.Action;
import domain.Report;

import java.util.List;

public interface ActionService {
    void addActionForReport(Action action, Report report);

    List<Action> findAllForReportById(Long reportId, int rowCount, int startFrom);

    Integer getRowCountForReportById(Long id);
}
