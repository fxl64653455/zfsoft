package com.zfsoft.boot.metrics.utils.jdbc;


import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zfsoft.boot.metrics.exception.MetricsException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Utility class for dealing with jdbc connections.
 */
public class JdbcUtils {
	
    private static final Logger LOG = LoggerFactory.getLogger(JdbcUtils.class);

    /**
     * Prevents instantiation.
     */
    private JdbcUtils() {
        //Do nothing
    }

    /**
     * Opens a new connection from this dataSource.
     *
     * @param dataSource The dataSource to obtain the connection from.
     * @return The new connection.
     * @throws FlywayException when the connection could not be opened.
     */
    public static Connection openConnection(DataSource dataSource) throws MetricsException {
        try {
            Connection connection = dataSource.getConnection();
            if (connection == null) {
                throw new MetricsException("Unable to obtain Jdbc connection from DataSource");
            }
            return connection;
        } catch (SQLException e) {
            throw new MetricsException("Unable to obtain Jdbc connection from DataSource", e);
        }
    }

    /**
     * Safely closes this connection. This method never fails.
     *
     * @param connection The connection to close.
     */
    public static void closeConnection(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            LOG.error("Error while closing Jdbc connection", e);
        }
    }

    /**
     * Safely closes this statement. This method never fails.
     *
     * @param statement The statement to close.
     */
    public static void closeStatement(Statement statement) {
        if (statement == null) {
            return;
        }

        try {
            statement.close();
        } catch (SQLException e) {
            LOG.error("Error while closing Jdbc statement", e);
        }
    }

    /**
     * Safely closes this resultSet. This method never fails.
     *
     * @param resultSet The resultSet to close.
     */
    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet == null) {
            return;
        }

        try {
            resultSet.close();
        } catch (SQLException e) {
            LOG.error("Error while closing Jdbc resultSet", e);
        }
    }
}
