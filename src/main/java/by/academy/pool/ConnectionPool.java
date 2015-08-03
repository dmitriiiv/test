package by.academy.pool;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

import static by.academy.util.PropertiesManager.*;

public class ConnectionPool {
	private static ConnectionPool instance;
    private BasicDataSource dataSource;

    private ConnectionPool() {
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(POOL.getProperty("db.driver"));
        dataSource.setUrl(POOL.getProperty("db.url"));
        dataSource.setUsername(POOL.getProperty("db.user"));
        dataSource.setPassword(POOL.getProperty("db.pass"));
    }

    public synchronized static ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
