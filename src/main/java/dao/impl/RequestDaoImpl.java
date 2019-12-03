package dao.impl;

import dao.DBConnector;
import dao.RequestDao;
import entity.request.RequestEntity;
import entity.user.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class RequestDaoImpl extends AbstractCrudDao<RequestEntity> implements RequestDao {
    private static final String SAVE_QUERY = "INSERT INTO requests (user_id, message)  values(?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM requests WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM requests";
    private static final String FIND_ALL_PAGINATION_QUERY = "SELECT * FROM requests LIMIT ?, ?";
    private static final String UPDATE_QUERY = "UPDATE requests SET user_id =?, message=? WHERE id = ?";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM requests WHERE id=?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) AS count FROM requests";

    public RequestDaoImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, FIND_ALL_PAGINATION_QUERY, UPDATE_QUERY, DELETE_BY_ID_QUERY, COUNT_QUERY);
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
}
