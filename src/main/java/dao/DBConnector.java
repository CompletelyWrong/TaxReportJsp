package dao;


import exception.DataBaseRuntimeException;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBConnector {
    private static final Logger LOGGER = Logger.getLogger(DBConnector.class);
    private static final BasicDataSource dataSource = new BasicDataSource();

    public DBConnector(String fileConfig) {
        ResourceBundle resource = ResourceBundle.getBundle(fileConfig);
        dataSource.setDriverClassName(resource.getString("db.driver"));
        dataSource.setUrl(resource.getString("db.url"));
        dataSource.setUsername(resource.getString("db.user"));
        dataSource.setPassword(resource.getString("db.password"));
        dataSource.setMinIdle(Integer.parseInt(resource.getString("db.minIdle")));
        dataSource.setMaxIdle(Integer.parseInt(resource.getString("db.maxIdle")));
        dataSource.setMaxOpenPreparedStatements(Integer.parseInt(resource.getString("db.maxOpenPreparedStatements")));

    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Connection was not established", e);
            throw new DataBaseRuntimeException("Connection not established", e);
        }
    }
}
