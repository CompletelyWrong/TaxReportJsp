package dao.impl;

import dao.DBConnector;
import dao.InspectorDao;
import entity.user.InspectorEntity;
import entity.user.Role;
import exception.DataBaseRuntimeException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class InspectorDaoImpl extends AbstractCrudDao<InspectorEntity> implements InspectorDao {
    private static final Logger LOGGER = Logger.getLogger(InspectorDaoImpl.class);

    private static final String SAVE_QUERY = "INSERT INTO inspectors (login, password, name, surname, patronym, role_id)  values(?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID_QUERY = "SELECT * FROM inspectors WHERE id = ?";
    private static final String FIND_ALL_QUERY = "SELECT * FROM inspectors WHERE role_id=2";
    private static final String FIND_ALL_PAGINATION_QUERY = "SELECT * FROM inspectors WHERE role_id=2 LIMIT ?, ?";
    private static final String UPDATE_QUERY = "UPDATE inspectors SET login =?, password=?, name=?,  surname=?, patronym=?, role_id=? WHERE id = ?";
    private static final String FIND_BY_LOGIN_QUERY = "SELECT * FROM inspectors WHERE login = ?";
    private static final String COUNT_QUERY = "SELECT COUNT(*) AS count FROM inspectors WHERE role_id=2";
    private static final String FIND_BY_USER = "SELECT id, login, password, name, surname, patronym, role_id FROM inspectors JOIN inspectors_users" +
            " ON inspectors.id=inspectors_users.inspector_id where inspectors_users.user_id=? and inspectors_users.active=true";
    private static final String FIND_WITH_LESS_USERS_QUERY = "SELECT id, login, password, name, surname, patronym, role_id FROM inspectors left join" +
            " inspectors_users on inspectors.id=inspectors_users.inspector_id where role_id=2  GROUP BY id order by COUNT(*) asc LIMIT 1";
    private static final String FIND_WITH_LESS_USERS_EXCEPT_THIS_ID_QUERY = "SELECT id, login, password, name, surname, patronym, role_id FROM inspectors " +
            "left join inspectors_users on inspectors.id=inspectors_users.inspector_id where role_id=2 and id<>?  GROUP BY id order by COUNT(*)  asc LIMIT 1";

    public InspectorDaoImpl(DBConnector connector) {
        super(connector, SAVE_QUERY, FIND_BY_ID_QUERY, FIND_ALL_QUERY, FIND_ALL_PAGINATION_QUERY, UPDATE_QUERY, COUNT_QUERY);
    }

    @Override
    public Optional<InspectorEntity> findByUserId(Long id) {
        return findByLongParam(id, FIND_BY_USER);
    }

    @Override
    public Optional<InspectorEntity> findWithLessUsers() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_WITH_LESS_USERS_QUERY)) {
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Optional.ofNullable(mapResultSetToEntity(resultSet)) : Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.error("Invalid insertion", e);
            throw new DataBaseRuntimeException("Invalid insertion", e);
        }
    }

    @Override
    public Optional<InspectorEntity> findWithLessUsersExceptThisId(Long inspectorId) {
        return findByLongParam(inspectorId, FIND_WITH_LESS_USERS_EXCEPT_THIS_ID_QUERY);
    }

    @Override
    protected InspectorEntity mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return new InspectorEntity.InspectorBuilder()
                .withId(resultSet.getLong("id"))
                .withEmail(resultSet.getString("login"))
                .withPassword(resultSet.getString("password"))
                .withName(resultSet.getString("name"))
                .withSurname(resultSet.getString("surname"))
                .withPatronymic(resultSet.getString("patronym"))
                .withRole(resultSet.getInt("role_id") == 1 ? Role.ADMIN : Role.INSPECTOR)
                .build();
    }

    @Override
    protected void insert(PreparedStatement preparedStatement, InspectorEntity inspectorEntity) throws SQLException {
        preparedStatement.setString(1, inspectorEntity.getEmail());
        preparedStatement.setString(2, inspectorEntity.getPassword());
        preparedStatement.setString(3, inspectorEntity.getName());
        preparedStatement.setString(4, inspectorEntity.getSurname());
        preparedStatement.setString(5, inspectorEntity.getPatronymic());
        preparedStatement.setInt(6, inspectorEntity.getRole().getRoleIndex());
    }

    @Override
    protected void updateValues(PreparedStatement preparedStatement, InspectorEntity inspectorEntity) throws SQLException {
        insert(preparedStatement, inspectorEntity);
        preparedStatement.setLong(7, inspectorEntity.getId());
    }

    @Override
    public Optional<InspectorEntity> findByEmail(String email) {
        return findByStringParam(email, FIND_BY_LOGIN_QUERY);
    }
}
