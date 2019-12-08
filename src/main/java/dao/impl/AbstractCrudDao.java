package dao.impl;

import dao.CrudDao;
import dao.DBConnector;
import exception.DataBaseRuntimeException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public abstract class AbstractCrudDao<E> implements CrudDao<E, Long> {
    private static final Logger LOGGER = Logger.getLogger(AbstractCrudDao.class);

    protected final DBConnector connector;
    private final String saveQuery;
    private final String findByIdQuery;
    private final String findAllQuery;
    private final String findAllPaginationQuery;
    private final String updateQuery;
    private final String countQuery;

    private static final BiConsumer<PreparedStatement, String> STRING_CONSUMER
            = (PreparedStatement pr, String param) -> {
        try {
            pr.setString(1, param);
        } catch (SQLException e) {
            LOGGER.error("STRING_CONSUMER " + e);
            throw new DataBaseRuntimeException("Insertion is failed", e);
        }
    };

    private static final BiConsumer<PreparedStatement, Long> LONG_CONSUMER
            = (PreparedStatement pr, Long param) -> {
        try {
            pr.setLong(1, param);
        } catch (SQLException e) {
            LOGGER.error("LONG_CONSUMER " + e);
            throw new DataBaseRuntimeException("Insertion is failed", e);
        }
    };

    AbstractCrudDao(DBConnector connector, String saveQuery, String findByIdQuery,
                    String findAllQuery, String findAllPaginationQuery, String updateQuery,
                    String countQuery) {
        this.connector = connector;
        this.saveQuery = saveQuery;
        this.findByIdQuery = findByIdQuery;
        this.findAllQuery = findAllQuery;
        this.findAllPaginationQuery = findAllPaginationQuery;
        this.updateQuery = updateQuery;
        this.countQuery = countQuery;
    }

    public Optional<E> findById(Long id) {
        return findByLongParam(id, findByIdQuery);
    }

    @Override
    public E save(E entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(saveQuery)) {

            insert(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Insertion is failed", e);
            throw new DataBaseRuntimeException("Insertion is failed", e);
        }
        return entity;
    }

    @Override
    public List<E> findAll(Integer currentPage, Integer recordsPerPage) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAllPaginationQuery)) {
            int start = currentPage * recordsPerPage - recordsPerPage;

            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, recordsPerPage);
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<E> entities = new ArrayList<>();
                while (resultSet.next()) {
                    entities.add(mapResultSetToEntity(resultSet));
                }
                return entities;
            }
        } catch (SQLException e) {
            LOGGER.error("Insertion is failed", e);
            throw new DataBaseRuntimeException("Insertion is failed", e);
        }
    }

    @Override
    public List<E> findAll() {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(findAllQuery)) {
            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                List<E> entities = new ArrayList<>();
                while (resultSet.next()) {
                    entities.add(mapResultSetToEntity(resultSet));
                }
                return entities;
            }
        } catch (SQLException e) {
            LOGGER.error("Insertion is failed", e);
            throw new DataBaseRuntimeException("Insertion is failed", e);
        }
    }

    @Override
    public void update(E entity) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            updateValues(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Insertion is failed", e);
            throw new DataBaseRuntimeException("Insertion is failed", e);
        }
    }

    public Integer getRowCountForLongParam(Long param, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, param);

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? resultSet.getInt("count") : 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Insertion is failed", e);
            throw new DataBaseRuntimeException("Insertion is failed", e);
        }
    }

    @Override
    public Integer countRows() {
        return findNumberOfRows(countQuery);
    }

    private Integer findNumberOfRows(String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            try (final ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? resultSet.getInt("count") : 0;
            }
        } catch (SQLException e) {
            LOGGER.error("Connection was not established", e);
            throw new DataBaseRuntimeException(e);
        }
    }

    Optional<E> findByLongParam(Long id, String query) {
        return findByParam(id, query, LONG_CONSUMER);
    }

    Optional<E> findByStringParam(String param, String query) {
        return findByParam(param, query, STRING_CONSUMER);
    }

    private <P> Optional<E> findByParam(P param, String query, BiConsumer<PreparedStatement, P> consumer) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            consumer.accept(preparedStatement, param);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next() ? Optional.ofNullable(mapResultSetToEntity(resultSet)) : Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.error("Insertion is failed", e);
            throw new DataBaseRuntimeException("Insertion is failed", e);
        }
    }

    protected abstract E mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    protected abstract void insert(PreparedStatement preparedStatement, E entity) throws SQLException;

    protected abstract void updateValues(PreparedStatement preparedStatement, E entity) throws SQLException;
}
