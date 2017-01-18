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
import by.newnet.domain.CreditCard;
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
	        "select * from users left join roles on users." + UsersTable.ROLE + " = roles."
	                + RolesTable.ID + " left join tariffs on users." + UsersTable.TARIFF + " = tariffs."
	                + TariffsTable.ID + " where " + UsersTable.ACCOUNT + " = ?";
	public static final String SAVE_PASSWORD = "update users set " + UsersTable.PASSWORD + "=? where " + UsersTable.ID + "=?";
	public static final String SAVE_CONTACTS = "update users set " + UsersTable.PHONE + "=?, " 
			+ UsersTable.EMAIL + "=? where " + UsersTable.ID + "=?";
	public static final String SHOW_USERS = "select * from users join roles on users."
	        + UsersTable.ROLE + " = roles." + RolesTable.ID + " join tariffs on users."
	        + UsersTable.TARIFF + " = tariffs." + TariffsTable.ID;
	public static final String GET_CARD_BY_NUMBER =
	        "select * from cards where " + CardsTable.NUMBER + " = ?";
	public static final String SET_ACCOUNT_BALANCE = "update users set "
	        + UsersTable.ACCOUNT_BALANCE + "=? where " + UsersTable.ACCOUNT + "=?";
	public static final String SET_CARD_BALANCE =
	        "update cards set " + CardsTable.BALANCE + "=? where " + CardsTable.NUMBER + "=?";
	public static final String ADD_NEW_CONTRACT = "INSERT INTO users (" +UsersTable.ACCOUNT+","
	        +UsersTable.FIRST_NAME+","+UsersTable.SECOND_NAME+") values('?','?','?')";

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
				if(rs.getString(UsersTable.TARIFF)!=null){
				tariff.setId(Integer.valueOf(rs.getString(TariffsTable.ID)));
				tariff.setName(rs.getString(TariffsTable.NAME));
				tariff.setPrice(new BigDecimal(rs.getString(TariffsTable.PRICE)));
				tariff.setSpeed(Integer.valueOf(rs.getString(TariffsTable.SPEED)));
				tariff.setTraffic(Integer.valueOf(rs.getString(TariffsTable.TRAFFIC)));
				// boolean ok here? values 1 or 0
				tariff.setInactive(Boolean.valueOf(rs.getString(TariffsTable.INACTIVE)));
				user.setTariff(tariff);
				}
				// what if user is not null but empty?
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
	public void setPassword(int userId, String password) throws DAOException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			savePassword(connection, userId,password);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (connection != null) {
					// connection.rollback();
					ConnectionPool.getInstance().releaseConnection(connection);
				}
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
	}

	@Override
	public void setContacts(int userId, String phone, String email) throws DAOException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			saveContacts(connection, userId, phone, email);
		} catch (ConnectionPoolException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (connection != null) {
					// connection.rollback();
					ConnectionPool.getInstance().releaseConnection(connection);
				}
			} catch (ConnectionPoolException e) {
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

	@Override
	public void pay(int userId, CreditCard card, BigDecimal amount) throws DAOException {
		Connection connection = null;
		PreparedStatement getCardStatement = null;
		PreparedStatement accountBalanceStatement = null;
		PreparedStatement cardBalanceStatement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			connection.setAutoCommit(false);
			getCardStatement = connection.prepareStatement(GET_CARD_BY_NUMBER);
			getCardStatement.setString(1, card.getNumber());
			ResultSet rsCard = getCardStatement.executeQuery();
			CreditCard cardDB = new CreditCard();
			while (rsCard.next()) {
				cardDB.setNumber(rsCard.getString(CardsTable.NUMBER));
				cardDB.setExpirationMonth(rsCard.getString(CardsTable.EXPIRATION_MONTH));
				cardDB.setExpirationYear(rsCard.getString(CardsTable.EXPIRATION_YEAR));
				cardDB.setSecurityCode(rsCard.getString(CardsTable.SECURITY_CODE));
				cardDB.setFirstName(rsCard.getString(CardsTable.FIRST_NAME));
				cardDB.setSecondName(rsCard.getString(CardsTable.SECOND_NAME));
				cardDB.setBalance(rsCard.getBigDecimal(CardsTable.BALANCE));
			}
			// check alsoif card is expired
			if (card.equals(cardDB) && (cardDB.getBalance()).compareTo(amount) >= 0) {
				User user = getUserById(userId);
				accountBalanceStatement = connection.prepareStatement(SET_ACCOUNT_BALANCE);
				accountBalanceStatement.setBigDecimal(1, user.getAccountBalance().add(amount));
				accountBalanceStatement.setString(2, user.getAccount());
				accountBalanceStatement.executeUpdate();
				cardBalanceStatement = connection.prepareStatement(SET_CARD_BALANCE);
				cardBalanceStatement.setBigDecimal(1, (cardDB.getBalance().subtract(amount)));
				cardBalanceStatement.setString(2, cardDB.getNumber());
				cardBalanceStatement.executeUpdate();
				connection.commit();
			} else{
				//smthg else
				throw new DAOException();
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (getCardStatement != null) {
					getCardStatement.close();
				}
				if (accountBalanceStatement != null) {
					accountBalanceStatement.close();
				}
				if (cardBalanceStatement != null) {
					cardBalanceStatement.close();
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
	public void register(int userId, String password, String reenterPassword, String phone, String email)
	        throws DAOException {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			connection.setAutoCommit(false);
			savePassword(connection, userId, password);
			saveContacts(connection, userId, phone, email);
			connection.commit();
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (connection != null) {
					// connection.rollback();
					ConnectionPool.getInstance().releaseConnection(connection);
				}
			} catch (ConnectionPoolException e) {
				throw new DAOException(e);
			}
		}
	}

	private void savePassword(Connection connection, int userId, String password) throws DAOException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SAVE_PASSWORD);
			statement.setString(1, password);
			statement.setInt(2, userId);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
	}

	private void saveContacts(Connection connection, int userId, String phone, String email)
	        throws DAOException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SAVE_CONTACTS);
			statement.setString(1, phone);
			statement.setString(2, email);
			statement.setInt(3, userId);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				throw new DAOException(e);
			}
		}
	}

	@Override
	public void addContract(String contract, String firstName, String secondName)
	        throws DAOException {
		Connection connection = null;
		PreparedStatement getUserStatement = null;
		PreparedStatement addContractStatement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			connection.setAutoCommit(false);
			getUserStatement = connection.prepareStatement(GET_USER_BY_ACCOUNT);
			getUserStatement.setString(1, contract);
			ResultSet rs = getUserStatement.executeQuery();
			if (!rs.next()) {
				addContractStatement = connection.prepareStatement(ADD_NEW_CONTRACT);
				addContractStatement.setString(1, contract);
				addContractStatement.setString(2, firstName);
				addContractStatement.setString(3, secondName);
				addContractStatement.executeUpdate();
				connection.commit();
			} else{
				// do smthg else!!
				throw new DAOException();
			}
		} catch (ConnectionPoolException | SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				if (getUserStatement != null) {
					getUserStatement.close();
				}
				if (addContractStatement != null) {
					addContractStatement.close();
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

	/*
	 * @Override public boolean registerUser(User user) throws DAOException {
	 * Connection connection = null; PreparedStatement statement = null;
	 * PreparedStatement statement2 = null; try { connection =
	 * ConnectionPool.getInstance().takeConnection();
	 * connection.setAutoCommit(false); statement =
	 * connection.prepareStatement(CHECK_USER); statement.setString(1,
	 * user.getAccount()); ResultSet rs = statement.executeQuery(); if
	 * (rs.next()) { connection.commit(); return false; }
	 * 
	 * statement2 = connection.prepareStatement(REGISTER_USER);
	 * statement2.setString(1, user.getAccount()); statement2.setString(2,
	 * user.getPassword()); statement2.setString(3, user.getFirstName());
	 * statement2.executeUpdate(); connection.commit(); } catch (SQLException |
	 * ConnectionPoolException e) { throw new DAOException(e); } finally { try {
	 * if (statement != null) { statement.close(); } if (statement2 != null) {
	 * statement2.close(); } if (connection != null) { // connection.rollback();
	 * ConnectionPool.getInstance().releaseConnection(connection); } } catch
	 * (ConnectionPoolException | SQLException e) { throw new DAOException(e); }
	 * } return true; }
	 */
}
