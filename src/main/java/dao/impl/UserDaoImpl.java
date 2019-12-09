package dao.impl;

import dao.DBConnector;
import dao.UserDao;
import entity.user.Role;
import entity.user.UserEntity;
import exception.DataBaseRuntimeException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractCrudDao<UserEntity> implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private static final String SAVE_QUERY = "INSERT INTO users (email, password, name, surname, patronymic, ind_code, role_id)  values(?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM users WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM users";
    private static final String FIND_ALL_QUERY_PAGINATION = "SELECT * FROM users LIMIT ?, ?";
    private static final String UPDATE_QUERY = "UPDATE users SET email =?, password=?, name=?,  surname=?, patronymic=?, ind_code=?, role_id=? WHERE id = ?";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT * FROM users WHERE email = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) AS count FROM users";
    private static final String SET_INSPECTOR_TO_USER_QUERY = "INSERT INTO inspectors_users (inspector_id, user_id, active) VALUES (?, ?, 'true')";
    private static final String UPDATE_INSPECTOR_TO_USER_QUERY = "UPDATE inspectors_users SET active='false' WHERE inspector_id = ? AND user_id = ?";
    private static final String FIND_BY_INSPECTOR_ID_PAGINATION_QUERY = "SELECT users.id, users.email, users.password, users.name, users.surname," +
            " users.patronymic, users.ind_code, users.role_id FROM mydb.users join mydb.inspectors_users " +
            "ON users.id=inspectors_users.user_id WHERE inspectors_users.inspector_id=? AND inspectors_users.active=true LIMIT ?, ?";
    private static final String COUNT_BY_INSPECTOR_ID = "SELECT count(*) as count FROM mydb.users JOIN mydb.inspectors_users " +
            "ON users.id=inspectors_users.user_id WHERE inspectors_users.inspector_id=? AND inspectors_users.active=true";


    public UserDaoImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, FIND_ALL_QUERY_PAGINATION, UPDATE_QUERY, COUNT_QUERY);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return findByStringParam(email, FIND_BY_EMAIL_QUERY);
    }

    @Override
    public Integer getRowCountByInspectorId(Long inspectorId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(COUNT_BY_INSPECTOR_ID)) {
            preparedStatement.setLong(1, inspectorId);

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? resultSet.getInt("count") : 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Invalid user search", e);
            throw new DataBaseRuntimeException("Invalid user search", e);
        }
    }

    @Override
    public void changeInspectorForUserById(UserEntity entity, Long newInspectorId) {
        try (Connection connection = connector.getConnection();
             PreparedStatement insertStatement = connection.prepareStatement(SET_INSPECTOR_TO_USER_QUERY);
             PreparedStatement updateStatement = connection.prepareStatement(UPDATE_INSPECTOR_TO_USER_QUERY)) {
            connection.setAutoCommit(false);
            updateStatement.setLong(1, entity.getInspectorEntity().getId());
            updateStatement.setLong(2, entity.getId());
            updateStatement.executeUpdate();

            insertStatement.setLong(1, newInspectorId);
            insertStatement.setLong(2, entity.getId());
            insertStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            LOGGER.error("Invalid insertion", e);
            throw new DataBaseRuntimeException("Invalid insertion", e);
        }
    }

    @Override
    public void setInspectorToUser(UserEntity entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SET_INSPECTOR_TO_USER_QUERY)) {
            preparedStatement.setLong(1, entity.getInspectorEntity().getId());
            preparedStatement.setLong(2, entity.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error("Invalid user insertion", e);
            throw new DataBaseRuntimeException("Invalid user insertion", e);
        }
    }

    @Override
    public List<UserEntity> findAllByInspectorId(Long inspectorId, Integer currentPage, Integer recordsPerPage) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_INSPECTOR_ID_PAGINATION_QUERY)) {
            int start = currentPage * recordsPerPage - recordsPerPage;

            preparedStatement.setLong(1, inspectorId);
            preparedStatement.setInt(2, start);
            preparedStatement.setInt(3, recordsPerPage);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<UserEntity> entities = new ArrayList<>();
                while (resultSet.next()) {
                    entities.add(mapResultSetToEntity(resultSet));
                }
                return entities;
            }
        } catch (SQLException e) {
            LOGGER.error("Invalid user search", e);
            throw new DataBaseRuntimeException("Invalid user search", e);
        }
    }

    @Override
    protected UserEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new UserEntity.UserBuilder()
                .withId(resultSet.getLong("id"))
                .withEmail(resultSet.getString("email"))
                .withPassword(resultSet.getString("password"))
                .withName(resultSet.getString("name"))
                .withSurname(resultSet.getString("surname"))
                .withPatronymic(resultSet.getString("patronymic"))
                .withRole(resultSet.getInt("role_id") == 4 ? Role.INDIVIDUAL_TAXPAYER : Role.LEGAL_TAXPAYER)
                .withIdentificationCode(resultSet.getInt("ind_code"))
                .build();
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, UserEntity entityUser) throws SQLException {
        preparedStatement.setString(1, entityUser.getEmail());
        preparedStatement.setString(2, entityUser.getPassword());
        preparedStatement.setString(3, entityUser.getName());
        preparedStatement.setString(4, entityUser.getSurname());
        preparedStatement.setString(5, entityUser.getPatronymic());
        preparedStatement.setInt(6, entityUser.getIdentificationCode());
        preparedStatement.setInt(7, entityUser.getRole().getRoleIndex());
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, UserEntity entityUser) throws SQLException {
        insert(preparedStatement, entityUser);
        preparedStatement.setLong(8, entityUser.getId());
    }
}
