package by.newnet.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import by.newnet.dao.exception.DAOException;
import by.newnet.dao.jdbc.pool.ConnectionPool;
import by.newnet.dao.jdbc.pool.ConnectionPoolException;

/**
 * The Class BaseJdbcDAO. The parent class to RequestJdbcDAO, TariffJdbcDAO, UserJdbcDAO. 
 * Provides a method to get a conenction and methods for closing statements, rollbacking
 * and releasing connection.
 */
public class BaseJdbcDAO {

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

/**
 * Gets the connection.
 *
 * @return the connection
 * @throws ConnectionPoolException the connection pool exception
 */
	public Connection getConnection() throws ConnectionPoolException {
		Connection connection = connectionPool.takeConnection();
		return connection;
	}

	/**
	 * Rollback connection.
	 *
	 * @param originalException the original exception
	 * @param connection the connection
	 * @throws DAOException the DAO exception
	 */
	public void rollbackConnection(SQLException originalException, Connection connection) throws DAOException {
		try {
			connection.rollback();
		} catch (SQLException rollbackException) {
			throw new DAOException(rollbackException.toString(), originalException);
		}
	}

	/**
	 * Close statements and release connection.
	 *
	 * @param connection the connection
	 * @param statements the statements
	 */
	public void closeStatementsAndReleaseConnection(Connection connection,
	        Statement... statements) {
		for (Statement statement : statements) {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// log
				}
			}
		}
		if (connection != null) {
			try {
				connectionPool.releaseConnection(connection);
			} catch (ConnectionPoolException e) {
				// log, comment
			}
		}

	}
}
