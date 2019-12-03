package service.impl;

import dao.ActionDao;
import domain.Action;
import domain.Report;
import entity.action.ActionEntity;
import exception.EntityNotFoundException;
import exception.InvalidAddEntityException;
import exception.InvalidPaginationException;
import org.apache.log4j.Logger;
import service.ActionService;
import service.mapper.ActionMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ActionServiceImpl implements ActionService {
    private static final Logger LOGGER = Logger.getLogger(ActionServiceImpl.class);
    private final ActionDao actionDao;
    private final ActionMapper mapper;

    public ActionServiceImpl(ActionDao actionDao, ActionMapper mapper) {
        this.actionDao = actionDao;
        this.mapper = mapper;
    }

    @Override
    public void addActionForReport(Action action, Report report) {
        if (Objects.isNull(action) || Objects.isNull(report)) {
            LOGGER.error("addActionForReportByInspector - parameters are empty");
            throw new InvalidAddEntityException("Parameters are empty");
        }

        actionDao.save(mapper.mapActionToActionEntity(action, report));
    }

    @Override
    public List<Action> findAllForReportById(Long reportId, int rowCount, int startFrom) {
        if (Objects.isNull(reportId)) {
            LOGGER.error("parameters are empty");
            throw new EntityNotFoundException("Parameters are empty");
        }
        paginationValidating(rowCount, startFrom);
        List<ActionEntity> result = actionDao.findAllForReportById(reportId, rowCount, startFrom);

        return entityMapping(result);
    }

    @Override
    public Integer getRowCountForReportById(Long id) {
        return actionDao.getRowCountForReportById(id);
    }

    private void paginationValidating(int rowCount, int startFrom) {
        if (startFrom <= 0 || rowCount <= 0) {
            LOGGER.error("Invalid current page or records per page");
            throw new InvalidPaginationException("Invalid current page or records per page");
        }
    }

    private List<Action> entityMapping(List<ActionEntity> result) {
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapActionEntityToAction)
                .collect(Collectors.toList());
    }
}
