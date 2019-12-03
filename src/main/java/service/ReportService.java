package service;

import domain.Report;
import domain.User;

import java.util.List;

public interface ReportService {
    void addReportToUser(Report report, User user);

    void updateReportById(Report report, Long id);

    Report findById(Long id);

    List<Report> findReportsByUser(Long userId, int currentPage, int numOfRecords);

    List<Report> findAll(int currentPage, int numOfRecords);

    Integer getRowNumbers();

    Integer getRowNumbersOfListByUser(Long id);
}
