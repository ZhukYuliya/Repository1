package by.newnet.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.newnet.dao.UserDAO;
import by.newnet.dao.exception.DAOException;
import by.newnet.dao.jdbc.pool.ConnectionPool;
import by.newnet.dao.jdbc.pool.ConnectionPoolException;
import by.newnet.domain.Role;
import by.newnet.domain.Tariff;
import by.newnet.domain.User;

public class UserJdbcDAO implements UserDAO {
	public static final String CHECK_CREDENTIALS = "select * from users where " + UsersTable.ACCOUNT
	        + " = ? and " + UsersTable.PASSWORD + " = ?";
	public static final String REGISTER_USER =
	        "INSERT INTO users (login, password, name) values(?, ?, ?)";
	public static final String CHECK_USER =
	        "select " + UsersTable.ACCOUNT + " from users where " + UsersTable.ACCOUNT + " = ? ";
	public static final String POST_REQUEST =
	        "INSERT INTO requests (firstName, email, phone, address) values(?, ?, ?, ?)";
	// request
	public static final String SHOW_REQUESTS = "select requests." + " , " + "genres."
	        + UsersTable.FIRST_NAME + " from books join genres on books.genre = genres.id";
	public static final String SUBSCRIBE_FOR_TARIFF = "INSERT INTO accounts (tariff_id) values(?)";
	public static final String GET_USER = "select * from users where user.id = ?";
	public static final String SET_PASSWORD = "INSERT INTO users (password) values(?)";

	/*
	 * public static final String SHOW_ACCOUNT_INFO = "select users." +
	 * UsersTable.ACCOUNT + " , " + "users." + UsersTable.ACCOUNT_BALANCE +
	 * UsersTable.BANNED + TariffsTable.NAME +
	 * " from users join tariffs on users.tariff_id = tariffs.id where users.id = ?"
	 * ;
	 */
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
				loggedUser.setFirstName(rs.getString(UsersTable.FIRST_NAME));
				loggedUser.setFirstName(rs.getString(UsersTable.SECOND_NAME));
				Role role = Role.valueOf(rs.getString(UsersTable.ROLE));
				loggedUser.setRole(role);
				// which other data is set at authentication?
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
			if (rs.next()) {
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

	@Override
	public boolean postRequest(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(POST_REQUEST);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPhone());
			statement.setString(4, user.getAddress());
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					// why rollback? set autocommit true needed?
					connection.rollback();
					ConnectionPool.getInstance().releaseConnection(connection);
				}
			} catch (ConnectionPoolException | SQLException e) {
				throw new DAOException(e);
			}
		}
		return false;
	}

	@Override
	public List<User> showRequests() throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(SHOW_REQUESTS);
			ResultSet rs = statement.executeQuery();
			List<User> requestsList = new ArrayList<User>();
			while (rs.next()) {
				User preCustomer = new User();
				// change tables names
				preCustomer.setFirstName(rs.getString(TariffsTable.DESCRIPTION));
				preCustomer.setEmail(rs.getString(UsersTable.NAME));
				preCustomer.setPhone(rs.getString(UsersTable.NAME));
				preCustomer.setAddress(rs.getString(UsersTable.NAME));
				// set handled?
				requestsList.add(preCustomer);
			}
			return requestsList;
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
		public User getUser(int userId) throws DAOException {
			Connection connection = null;
			PreparedStatement statement = null;
			User user = new User();
			Role role = new Role();
			Tariff tariff = new Tariff();
			try {
				connection = ConnectionPool.getInstance().takeConnection();
				//not transaction?
				//connection.setAutoCommit(false);
				statement = connection.prepareStatement(GET_USER);
				ResultSet rs = statement.executeQuery();
				while (rs.next()) {
					user.setId(rs.getInt(UsersTable.ID));
					user.setAccount(rs.getInt(UsersTable.ACCOUNT));
					user.setPassword(rs.getString(UsersTable.PASSWORD));
					user.setEmail(rs.getString(UsersTable.EMAIL));
					//convert to bigdecimal?
					user.setAccountBalance(rs.getBigDecimal(UsersTable.ACCOUNT_BALANCE));
					role.setName(rs.getString(UsersTable.ROLE));
					user.setRole(role);
					user.setBanned(rs.getBoolean(UsersTable.BANNED));
					user.setFirstName(rs.getString(UsersTable.FIRST_NAME));
					user.setSecondName(rs.getString(UsersTable.SECOND_NAME));
					tariff.setName(rs.getString(UsersTable.TARIFF);
					user.setTariff(tariff);
				}
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
			return user;
		}

	@Override
	public void subscribeTariff(int newTariffId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			// not transaction?
			// connection.setAutoCommit(false);
			statement = connection.prepareStatement(SUBSCRIBE_FOR_TARIFF);
			statement.setInt(1, newTariffId);
			statement.executeUpdate();
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					// rollback needed?
					connection.rollback();
					ConnectionPool.getInstance().releaseConnection(connection);
				}
			} catch (ConnectionPoolException | SQLException e) {
				throw new DAOException(e);
			}
		}
	}

	@Override
	public void setPassword(String newPassword) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(SET_PASSWORD);
			statement.setString(1, newPassword);
		} catch (SQLException | ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
				if (connection != null) {
					connection.rollback();
					ConnectionPool.getInstance().releaseConnection(connection);
				}
			} catch (ConnectionPoolException | SQLException e) {
				throw new DAOException(e);
			}
		}
	}

}
