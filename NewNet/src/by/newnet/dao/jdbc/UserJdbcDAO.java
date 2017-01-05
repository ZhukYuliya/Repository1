package by.newnet.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.newnet.dao.UserDAO;
import by.newnet.dao.exception.DAOException;
import by.newnet.dao.jdbc.pool.ConnectionPool;
import by.newnet.dao.jdbc.pool.ConnectionPoolException;
import by.newnet.domain.User;

public class UserJdbcDAO implements UserDAO {
	public static final String CHECK_CREDENTIALS =
	        "select * from users where " + UsersTable.LOGIN + " = ? and " + UsersTable.PASSWORD + " = ?";
	public static final String REGISTER_USER = "INSERT INTO users (login, password, name) values(?, ?, ?)";
	public static final String CHECK_USER =
	        "select " + UsersTable.LOGIN + " from users where " + UsersTable.LOGIN + " = ? ";
	@Override
	public User checkAuthorisationData(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(CHECK_CREDENTIALS);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			ResultSet rs = statement.executeQuery();
			User loggedUser = null;
			if (rs.next()) {
				loggedUser = new User();
				loggedUser.setId(rs.getInt(UsersTable.ID));
				loggedUser.setName(rs.getString(UsersTable.NAME));
				loggedUser.setLogin(rs.getString(UsersTable.LOGIN));
				loggedUser.setPassword(rs.getString(UsersTable.PASSWORD));
			}
			return loggedUser;
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					ConnectionPool.getInstance().releaseConnection(connection);
				}
			} catch (ConnectionPoolException | SQLException e) {
				throw new DAOException(e);
			}
		}
	}

	@Override
	public boolean registerUser(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(CHECK_USER);
			statement.setString(1, user.getLogin());
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				connection.commit();
				return false;
			}

			statement2 = connection.prepareStatement(REGISTER_USER);
			statement2.setString(1, user.getLogin());
			statement2.setString(2, user.getPassword());
			statement2.setString(3, user.getName());
			statement2.executeUpdate();		
			connection.commit();
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (statement2 != null) {
					statement2.close();
				}
				if (connection != null) {
					connection.rollback();
					ConnectionPool.getInstance().releaseConnection(connection);
				}
			} catch (ConnectionPoolException | SQLException e) {
				throw new DAOException(e);
			}
		}
		return true;
	}

}
