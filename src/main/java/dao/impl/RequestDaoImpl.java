package dao.impl;

import dao.DBConnector;
import dao.RequestDao;
import entity.request.RequestEntity;
import entity.user.UserEntity;
import exception.DataBaseRuntimeException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RequestDaoImpl extends AbstractCrudDao<RequestEntity> implements RequestDao {
    private static final Logger LOGGER = Logger.getLogger(RequestDaoImpl.class);

    private static final String SAVE_QUERY = "INSERT INTO requests (user_id, message)  values(?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM requests WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM requests";
    private static final String FIND_ALL_PAGINATION_QUERY = "SELECT * FROM requests LIMIT ?, ?";
    private static final String UPDATE_QUERY = "UPDATE requests SET user_id =?, message=? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM requests WHERE id=?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) AS count FROM requests";

    public RequestDaoImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, FIND_ALL_PAGINATION_QUERY, UPDATE_QUERY, COUNT_QUERY);
    }

    @Override
    protected RequestEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new RequestEntity(resultSet.getLong("id"),
                new UserEntity.UserBuilder().withId(resultSet.getLong("user_id")).build(),
                resultSet.getString("message"));
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, RequestEntity entity) throws SQLException {
        preparedStatement.setInt(1, entity.getEntityUser().getId().intValue());
        preparedStatement.setString(2, entity.getReason());
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, RequestEntity entity) throws SQLException {
        insert(preparedStatement, entity);
        preparedStatement.setLong(3, entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Invalid deleting entities by id", e);
            throw new DataBaseRuntimeException("Invalid deleting entities by id", e);
        }
    }
}
