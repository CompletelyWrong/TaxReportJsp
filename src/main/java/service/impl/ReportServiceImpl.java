package service.impl;

import dao.ReportDao;
import domain.Report;
import domain.User;
import entity.report.ReportEntity;
import exception.EntityNotFoundException;
import exception.InvalidAddEntityException;
import exception.InvalidPaginationException;
import exception.InvalidUpdateEntityException;
import org.apache.log4j.Logger;
import service.ReportService;
import service.mapper.ReportMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
        if (Objects.isNull(report) || Objects.isNull(user)) {
            LOGGER.error("Parameters are empty");
            throw new InvalidAddEntityException("Parameters are empty");
        }

        reportDao.save(mapper.mapReportToReportEntity(report, user));
    }

    @Override
    public void updateReportById(Report report, Long id) {
        if (Objects.isNull(report) || Objects.isNull(id)) {
            LOGGER.error("Parameters are empty");
            throw new InvalidUpdateEntityException("Parameters are empty");
        }

        reportDao.update(mapper.mapReportToReportEntity(report, id));
    }

    @Override
    public Report findById(Long id) {
        return mapper.mapReportEntityToReport(reportDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("report not found")));
    }

    @Override
    public List<Report> findReportsByUser(Long userId, int currentPage, int numOfRecords) {
        if (Objects.isNull(userId)) {
            LOGGER.error("Parameters are empty");
            throw new EntityNotFoundException("Parameters are empty");
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
