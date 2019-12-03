package dao;

import entity.report.ReportEntity;

import java.util.List;

public interface ReportDao extends CrudDao<ReportEntity, Long> {
    List<ReportEntity> findByUser(Long userId, Integer currentPage, Integer recordsPerPage);

    Integer countRowsForUserById(Long id);
}
