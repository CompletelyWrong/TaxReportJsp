package dao;

import entity.action.ActionEntity;

import java.util.List;

public interface ActionDao extends CrudDao<ActionEntity, Long> {
    List<ActionEntity> findAllForReportById(Long id, Integer currentPage, Integer recordsPerPage);

    Integer getRowCountForReportById(Long id);
}
