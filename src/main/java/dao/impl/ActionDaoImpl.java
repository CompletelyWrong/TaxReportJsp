package dao.impl;

import dao.ActionDao;
import dao.DBConnector;
import entity.action.ActionEntity;
import entity.action.ActionType;
import entity.report.ReportEntity;
import entity.user.InspectorEntity;
import exception.DataBaseRuntimeException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActionDaoImpl extends AbstractCrudDao<ActionEntity> implements ActionDao {
    private static final Logger LOGGER = Logger.getLogger(ActionDaoImpl.class);

    private static final String SAVE_QUERY = "INSERT INTO actions (inspector_id, date, mesage, action_type_id, report_id)  values(?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM actions WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM actions";
    private static final String FIND_ALL_PAGINATION_QUERY = "SELECT * FROM actions";
    private static final String UPDATE_QUERY = "UPDATE actions SET inspector_id =?, date=?, mesage=?,  action_type_id=?, report_id=? WHERE id = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) AS count FROM actions";
    private static final String FIND_BY_ID_QUERY_PAGINATION = "SELECT * FROM actions WHERE report_id = ? LIMIT ?, ?";
    private static final String COUNT_BY_ID = "SELECT COUNT(*) AS count FROM actions WHERE report_id = ?";

    public ActionDaoImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, FIND_ALL_PAGINATION_QUERY, UPDATE_QUERY, COUNT_QUERY);
    }

    @Override
    public Integer getRowCountForReportById(Long id) {
        return getRowCountForLongParam(id, COUNT_BY_ID);
    }

    @Override
    protected ActionEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ActionEntity.builder().
                withId(resultSet.getLong("id"))
                .withInspector(new InspectorEntity.InspectorBuilder().withId((long) resultSet.getInt("inspector_id")).build())
                .withDate(resultSet.getTimestamp("date").toLocalDateTime())
                .withMessage(resultSet.getString("mesage"))
                .withActionType(setActionType(resultSet.getInt("action_type_id")))
                .withReport(ReportEntity.builder().withId((long) resultSet.getInt("report_id")).build())
                .build();
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, ActionEntity actionEntity) throws SQLException {
        preparedStatement.setInt(1, actionEntity.getInspectorEntity().getId().intValue());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(actionEntity.getDateTime()));
        preparedStatement.setString(3, actionEntity.getMessage());
        preparedStatement.setInt(4, actionEntity.getAction().getIndex());
        preparedStatement.setInt(5, actionEntity.getReportEntity().getId().intValue());
    }

    @Override
    public List<ActionEntity> findAllForReportById(Long reportId, Integer currentPage, Integer recordsPerPage) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY_PAGINATION)) {
            int start = currentPage * recordsPerPage - recordsPerPage;
            preparedStatement.setLong(1, reportId);
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, recordsPerPage);

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<ActionEntity> entities = new ArrayList<>();
                while (resultSet.next()) {
                    entities.add(mapResultSetToEntity(resultSet));
                }
                return entities;
            }
        } catch (SQLException e) {
            LOGGER.error("Invalid insertion", e);
            throw new DataBaseRuntimeException("Invalid insertion", e);
        }
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, ActionEntity actionEntity) throws SQLException {
        insert(preparedStatement, actionEntity);
        preparedStatement.setLong(6, actionEntity.getId());
    }

    private ActionType setActionType(int index) {
        switch (index) {
            case 2:
                return ActionType.ACCEPT;
            case 3:
                return ActionType.REJECT;
            case 4:
                return ActionType.REQUEST_CHANGES;
            default:
                return ActionType.VIEW;
        }
    }
}
