package by.newnet.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import by.newnet.dao.exception.DAOException;
import by.newnet.dao.jdbc.pool.ConnectionPool;
import by.newnet.dao.jdbc.pool.ConnectionPoolException;

public class BaseJdbcDAO {

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();
//to change con pool in 1 place instead 3
	public Connection getConnection() throws ConnectionPoolException {
		Connection connection = connectionPool.takeConnection();
		return connection;
	}

	public void rollbackConnection(SQLException originalException, Connection connection) throws DAOException {
		try {
			connection.rollback();
		} catch (SQLException rollbackException) {
			throw new DAOException(rollbackException.toString(), originalException);
		}
	}

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
