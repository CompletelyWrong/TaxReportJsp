package dao.impl;

import dao.DBConnector;
import dao.ReportDao;
import entity.report.ReportEntity;
import entity.report.ReportStatus;
import entity.user.UserEntity;
import exception.DataBaseRuntimeException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportDaoImpl extends AbstractCrudDao<ReportEntity> implements ReportDao {
    private static final Logger LOGGER = Logger.getLogger(ReportDaoImpl.class);
    private final DBConnector connector;

    private static final String SAVE_QUERY = "INSERT INTO reports (file_link, date_of_add, status_id, users_id)  values(?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM reports WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM reports";
    private static final String FIND_ALL_QUERY_PAGINATION = "SELECT * FROM reports LIMIT ?, ?";
    private static final String UPDATE_QUERY = "UPDATE reports SET file_link =?, date_of_add=?, status_id=?,  users_id=? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "UNSUPPORTED";
    private static final String FIND_BY_USER_QUERY = "SELECT * FROM reports WHERE users_id = ? LIMIT ?, ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) AS count FROM reports";
    private static final String COUNT_QUERY_FOR_USER = "SELECT COUNT(*) AS count FROM reports WHERE users_id=?";


    public ReportDaoImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, FIND_ALL_QUERY_PAGINATION, UPDATE_QUERY, DELETE_BY_ID_QUERY, COUNT_QUERY);
        this.connector = connector;
    }

    @Override
    public List<ReportEntity> findByUser(Long userId, Integer currentPage, Integer recordsPerPage) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER_QUERY)) {
            int start = currentPage * recordsPerPage - recordsPerPage;

            preparedStatement.setLong(1, userId);
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, recordsPerPage);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<ReportEntity> entities = new ArrayList<>();
                while (resultSet.next()) {
                    entities.add(mapResultSetToEntity(resultSet));
                }
                return entities;
            }
        } catch (SQLException e) {
            LOGGER.error("Invalid report search", e);
            throw new DataBaseRuntimeException("Invalid report search", e);
        }
    }

    @Override
    public Integer countRowsForUserById(Long id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_QUERY_FOR_USER)) {
            preparedStatement.setLong(1, id);

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? resultSet.getInt("count") : 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Invalid count rows report", e);
            throw new DataBaseRuntimeException("Invalid count rows report", e);
        }
    }

    @Override
    protected ReportEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return ReportEntity.builder()
                .withId(resultSet.getLong("id"))
                .withUser(new UserEntity.UserBuilder().withId(resultSet.getLong("users_id")).build())
                .withCreationDate(resultSet.getTimestamp("date_of_add").toLocalDateTime())
                .withFileLink(resultSet.getString("file_link"))
                .withStatus(setReportStatus(resultSet.getInt("status_id")))
                .build();
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, ReportEntity reportEntity) throws SQLException {
        preparedStatement.setString(1, reportEntity.getFileLink());
        preparedStatement.setTimestamp(2, Timestamp.valueOf(reportEntity.getCreationDate()));
        preparedStatement.setInt(3, reportEntity.getStatus().getIndex());
        preparedStatement.setInt(4, reportEntity.getEntityUser().getId().intValue());
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, ReportEntity reportEntity) throws SQLException {
        insert(preparedStatement, reportEntity);
        preparedStatement.setLong(5, reportEntity.getId());
    }

    private ReportStatus setReportStatus(int index) {
        switch (index) {
            case 2:
                return ReportStatus.ACCEPTED;
            case 3:
                return ReportStatus.REJECTED;
            case 4:
                return ReportStatus.UPDATED;
            default:
                return ReportStatus.NEW;
        }
    }
}
