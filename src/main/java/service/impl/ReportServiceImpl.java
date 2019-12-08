package service.impl;

import dao.ReportDao;
import domain.Report;
import domain.User;
import entity.report.ReportEntity;
import exception.EntityNotFoundException;
import exception.InvalidPaginationException;
import org.apache.log4j.Logger;
import service.ReportService;
import service.mapper.ReportMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class ReportServiceImpl implements ReportService {
    private static final Logger LOGGER = Logger.getLogger(ReportServiceImpl.class);

    private final ReportDao reportDao;
    private final ReportMapper mapper;

    public ReportServiceImpl(ReportDao reportDao, ReportMapper mapper) {
        this.reportDao = reportDao;
        this.mapper = mapper;
    }

    @Override
    public void addReportToUser(Report report, User user) {
        if (isNull(report) || isNull(user)) {
            LOGGER.error("Report or user is null");
            throw new IllegalArgumentException("Report or user is null");
        }

        reportDao.save(mapper.mapReportToReportEntity(report, user));
    }

    @Override
    public void updateReport(Report report) {
        if (isNull(report)) {
            LOGGER.error("Report is null");
            throw new IllegalArgumentException("Report is null");
        }

        reportDao.update(mapper.mapReportToReportEntity(report));
    }

    @Override
    public Report findById(Long reportId) {
        if (isNull(reportId)) {
            LOGGER.error("Report id is null");
            throw new IllegalArgumentException("Report id is null");
        }

        return reportDao.findById(reportId)
                .map(mapper::mapReportEntityToReport)
                .orElseThrow(() -> {
                    LOGGER.warn("There is no report with this such id");
                    return new EntityNotFoundException("There is no report with this such id");
                });
    }

    @Override
    public List<Report> findReportsByUser(Long userId, int currentPage, int numOfRecords) {
        if (isNull(userId)) {
            LOGGER.error("User id is null");
            throw new EntityNotFoundException("User id is null");
        }
        paginationValidating(currentPage, numOfRecords);
        List<ReportEntity> result = reportDao.findByUser(userId, currentPage, numOfRecords);

        return entityMapping(result);
    }

    @Override
    public List<Report> findAll(int rowCount, int startFrom) {
        paginationValidating(rowCount, startFrom);
        List<ReportEntity> result = reportDao.findAll(rowCount, startFrom);

        return entityMapping(result);
    }

    @Override
    public Integer getRowNumbers() {
        return reportDao.countRows();
    }

    @Override
    public Integer getRowNumbersOfListByUser(Long id) {
        return reportDao.countRowsForUserById(id);
    }

    private void paginationValidating(int rowCount, int startFrom) {
        if (startFrom <= 0 || rowCount <= 0) {
            LOGGER.error("Invalid current page or records per page");
            throw new InvalidPaginationException("Invalid current page or records per page");
        }
    }

    private List<Report> entityMapping(List<ReportEntity> result) {
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapReportEntityToReport)
                .collect(Collectors.toList());
    }
}
