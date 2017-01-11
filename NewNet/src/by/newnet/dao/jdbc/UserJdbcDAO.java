package by.newnet.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	public static final String SUBSCRIBE_FOR_TARIFF =
	        "update users set " + UsersTable.TARIFF + " = ? where " + UsersTable.ID + " = ?";
	public static final String GET_USER_BY_ID =
	        "select * from users where " + UsersTable.ID + " = ?";
	public static final String GET_USER_BY_ACCOUNT =
	        "select * from users join roles on users." + UsersTable.ROLE + " = roles."
	                + RolesTable.ID + " join tariffs on users." + UsersTable.TARIFF + " = tariffs."
	                + TariffsTable.ID + " where " + UsersTable.ACCOUNT + " = ? ";

	public static final String SET_PASSWORD = "INSERT INTO users (password) values(?)";
	public static final String SHOW_USERS = "select * from users join roles on users."
	        + UsersTable.ROLE + " = roles." + RolesTable.ID + " join tariffs on users."
	        + UsersTable.TARIFF + " = tariffs." + TariffsTable.ID;

	/*
	 * public static final String SHOW_ACCOUNT_INFO = "select users." +
	 * UsersTable.ACCOUNT + " , " + "users." + UsersTable.ACCOUNT_BALANCE +
	 * UsersTable.BANNED + TariffsTable.NAME +
	 * " from users join tariffs on users.tariff_id = tariffs.id where users.id = ?"
	 * ;
	 */
	/*
	 * @Override public User checkAuthorisationData(User user) throws
	 * DAOException { Connection connection = null; PreparedStatement statement
	 * = null; try { connection = ConnectionPool.getInstance().takeConnection();
	 * statement = connection.prepareStatement(CHECK_CREDENTIALS);
	 * statement.setString(1, user.getAccount()); statement.setString(2,
	 * user.getPassword()); ResultSet rs = statement.executeQuery(); User
	 * loggedUser = null; if (rs.next()) { loggedUser = new User();
	 * loggedUser.setId(rs.getInt(UsersTable.ID));
	 * loggedUser.setAccount(rs.getString(UsersTable.ACCOUNT));
	 * loggedUser.setPassword(rs.getString(UsersTable.PASSWORD));
	 * loggedUser.setEmail(rs.getString(UsersTable.EMAIL));
	 * loggedUser.setAccountBalance(rs.getBigDecimal(UsersTable.ACCOUNT_BALANCE)
	 * ); // set id or name? Role role = new Role();
	 * role.setId(rs.getInt(UsersTable.ROLE));
	 * //role.setName(rs.getString(UsersTable.ROLE)); user.setRole(role);
	 * user.setBanned(rs.getBoolean(UsersTable.BANNED));
	 * user.setFirstName(rs.getString(UsersTable.FIRST_NAME));
	 * user.setSecondName(rs.getString(UsersTable.SECOND_NAME)); Tariff tariff =
	 * new Tariff(); // need to creat tariff or just set tariff id?
	 * tariff.setName(rs.getString(UsersTable.TARIFF)); user.setTariff(tariff);
	 * loggedUser.setFirstName(rs.getString(UsersTable.FIRST_NAME));
	 * loggedUser.setSecondName(rs.getString(UsersTable.SECOND_NAME));
	 * loggedUser.setPhone(rs.getString(UsersTable.PHONE)); } return loggedUser;
	 * } catch (SQLException | ConnectionPoolException e) { throw new
	 * DAOException(e); } finally { try { if (statement != null) {
	 * statement.close(); } if (connection != null) {
	 * ConnectionPool.getInstance().releaseConnection(connection); } } catch
	 * (ConnectionPoolException | SQLException e) { throw new DAOException(e); }
	 * } }
	 */

	@Override
	public boolean registerUser(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(CHECK_USER);
			statement.setString(1, user.getAccount());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				connection.commit();
				return false;
			}

			statement2 = connection.prepareStatement(REGISTER_USER);
			statement2.setString(1, user.getAccount());
			statement2.setString(2, user.getPassword());
			statement2.setString(3, user.getFirstName());
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
					// connection.rollback();
					ConnectionPool.getInstance().releaseConnection(connection);
				}
			} catch (ConnectionPoolException | SQLException e) {
				throw new DAOException(e);
			}
		}
		return true;
	}

	@Override
	public User getUserById(int userId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		User user = new User();
		Role role = new Role();
		Tariff tariff = new Tariff();
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			// not transaction?
			// connection.setAutoCommit(false);
			statement = connection.prepareStatement(GET_USER_BY_ID);
			statement.setInt(1, userId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt(UsersTable.ID));
				user.setAccount(rs.getString(UsersTable.ACCOUNT));
				user.setPassword(rs.getString(UsersTable.PASSWORD));
				user.setEmail(rs.getString(UsersTable.EMAIL));
				// convert to bigdecimal?
				user.setAccountBalance(rs.getBigDecimal(UsersTable.ACCOUNT_BALANCE));
				role.setName(rs.getString(UsersTable.ROLE));
				user.setRole(role);
				user.setBanned(rs.getBoolean(UsersTable.BANNED));
				user.setFirstName(rs.getString(UsersTable.FIRST_NAME));
				user.setSecondName(rs.getString(UsersTable.SECOND_NAME));
				tariff.setName(rs.getString(UsersTable.TARIFF));
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
	public User getUserByAccount(String accountNumber) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		User user = new User();
		Role role = new Role();
		Tariff tariff = new Tariff();
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			// not transaction?
			// connection.setAutoCommit(false);
			statement = connection.prepareStatement(GET_USER_BY_ACCOUNT);
			statement.setString(1, accountNumber);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt(UsersTable.ID));
				user.setAccount(rs.getString(UsersTable.ACCOUNT));
				user.setPassword(rs.getString(UsersTable.PASSWORD));
				user.setEmail(rs.getString(UsersTable.EMAIL));
				// convert to bigdecimal?
				user.setAccountBalance(rs.getBigDecimal(UsersTable.ACCOUNT_BALANCE));
				role.setId(Integer.valueOf(rs.getString(RolesTable.ID)));
				role.setName(rs.getString(RolesTable.NAME));
				user.setRole(role);
				user.setBanned(rs.getBoolean(UsersTable.BANNED));
				user.setFirstName(rs.getString(UsersTable.FIRST_NAME));
				user.setSecondName(rs.getString(UsersTable.SECOND_NAME));
				// mb use get tariff dao method?
				tariff.setId(Integer.valueOf(rs.getString(TariffsTable.ID)));
				tariff.setName(rs.getString(TariffsTable.NAME));
				tariff.setPrice(new BigDecimal(rs.getString(TariffsTable.PRICE)));
				tariff.setSpeed(Integer.valueOf(rs.getString(TariffsTable.SPEED)));
				tariff.setTraffic(Integer.valueOf(rs.getString(TariffsTable.TRAFFIC)));
				// boolean ok here? values 1 or 0
				tariff.setInactive(Boolean.valueOf(rs.getString(TariffsTable.INACTIVE)));
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
	public void subscribeTariff(int userId, int newTariffId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			// not transaction?
			// connection.setAutoCommit(false);
			statement = connection.prepareStatement(SUBSCRIBE_FOR_TARIFF);
			statement.setInt(1, newTariffId);
			statement.setInt(2, userId);
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
					// why rollback?
					// connection.rollback();
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
					// connection.rollback();
					ConnectionPool.getInstance().releaseConnection(connection);
				}
			} catch (ConnectionPoolException | SQLException e) {
				throw new DAOException(e);
			}
		}
	}

	@Override
	public List<User> showUsers() throws DAOException {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SHOW_USERS);
			List<User> usersList = new ArrayList<User>();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(UsersTable.ID));
				user.setFirstName(rs.getString(UsersTable.FIRST_NAME));
				user.setSecondName(rs.getString(UsersTable.SECOND_NAME));
				user.setEmail(rs.getString(UsersTable.EMAIL));
				user.setAccount(rs.getString(UsersTable.ACCOUNT));
				user.setPassword(rs.getString(UsersTable.PASSWORD));
				user.setPassword(rs.getString(UsersTable.PASSWORD));
				Role role = new Role();
				role.setId(rs.getInt(RolesTable.ID));
				role.setName(rs.getString(RolesTable.NAME));
				user.setRole(role);
				user.setAccountBalance(rs.getBigDecimal((UsersTable.ACCOUNT_BALANCE)));
				user.setBanned(rs.getBoolean(UsersTable.BANNED));
				user.setPhone(rs.getString(UsersTable.PHONE));
				Tariff tariff = new Tariff();
				tariff.setId(rs.getInt(TariffsTable.ID));
				tariff.setName(rs.getString(TariffsTable.NAME));
				tariff.setPrice(new BigDecimal(rs.getString(TariffsTable.PRICE)));
				tariff.setSpeed(Integer.valueOf(rs.getString(TariffsTable.SPEED)));
				tariff.setTraffic(Integer.valueOf(rs.getString(TariffsTable.TRAFFIC)));
				// boolean ok here? values 1 or 0
				tariff.setInactive(Boolean.valueOf(rs.getString(TariffsTable.INACTIVE)));
				user.setTariff(tariff);
				usersList.add(user);
			}
			return usersList;
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
}
