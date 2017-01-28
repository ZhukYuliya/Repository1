package by.newnet.dao.jdbc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.newnet.dao.UserDAO;
import by.newnet.dao.exception.DAOException;
import by.newnet.domain.CreditCard;
import by.newnet.domain.Role;
import by.newnet.domain.Tariff;
import by.newnet.domain.User;

public class UserJdbcDAO extends BaseJdbcDAO implements UserDAO {
	public static final String CHECK_CREDENTIALS = "select * from users where " + UsersTable.ACCOUNT
	        + " = ? and " + UsersTable.PASSWORD + " = ?";
	public static final String REGISTER_USER =
	        "INSERT INTO users (login, password, name, draft) values(?, ?, ?, false)";
	public static final String CHECK_USER =
	        "select " + UsersTable.ACCOUNT + " from users where " + UsersTable.ACCOUNT + " = ? ";
	public static final String SUBSCRIBE_FOR_TARIFF =
	        "update users set " + UsersTable.TARIFF + " = ? where " + UsersTable.ID + " = ?";
	public static final String GET_USER_BY_ID =
	        "select * from users left join roles on users." + UsersTable.ROLE + " = roles."
	                + RolesTable.ID + " left join tariffs on users." + UsersTable.TARIFF
	                + " = tariffs." + TariffsTable.ID + " where " + UsersTable.ID + " = ?";
	public static final String GET_USER_BY_ACCOUNT =
	        "select * from users left join roles on users." + UsersTable.ROLE + " = roles."
	                + RolesTable.ID + " left join tariffs on users." + UsersTable.TARIFF
	                + " = tariffs." + TariffsTable.ID + " where " + UsersTable.ACCOUNT + " = ?";
	public static final String SAVE_PASSWORD =
	        "update users set " + UsersTable.PASSWORD + "=? where " + UsersTable.ID + "=?";
	public static final String SET_BALANCE =
	        "update users set " + UsersTable.ACCOUNT_BALANCE + "=? where " + UsersTable.ID + "=?";
	public static final String SAVE_CONTACTS = "update users set " + UsersTable.PHONE + "=?, "
	        + UsersTable.EMAIL + "=? where " + UsersTable.ID + "=?";
	public static final String BLOCK_USER =
	        "update users set " + UsersTable.BLOCKED + "=true where " + UsersTable.ID + "=?";
	public static final String UNBLOCK_USER =
	        "update users set " + UsersTable.BLOCKED + "=false where " + UsersTable.ID + "=?";
	public static final String UPDATE_USER = "update users set " + UsersTable.ACCOUNT + "=?, "
	        + UsersTable.FIRST_NAME + "=?, " + UsersTable.SECOND_NAME
	        + "=?, " /* + UsersTable.ROLE + "=?, " */ + UsersTable.TARIFF + "=?, "
	        + UsersTable.BLOCKED + "=? " + " where " + UsersTable.ID + "=?";
	public static final String SHOW_USERS = "select * from users join roles on users."
	        + UsersTable.ROLE + " = roles." + RolesTable.ID + " join tariffs on users."
	        + UsersTable.TARIFF + " = tariffs." + TariffsTable.ID;
	public static final String GET_CARD_BY_NUMBER =
	        "select * from cards where " + CardsTable.NUMBER + " = ?";
	public static final String SET_ACCOUNT_BALANCE = "update users set "
	        + UsersTable.ACCOUNT_BALANCE + "=? where " + UsersTable.ACCOUNT + "=?";
	public static final String SET_CARD_BALANCE =
	        "update cards set " + CardsTable.BALANCE + "=? where " + CardsTable.NUMBER + "=?";
	public static final String SAVE_NEW_CONTRACT = "INSERT INTO users (" + UsersTable.ACCOUNT + ","
	        + UsersTable.FIRST_NAME + "," + UsersTable.SECOND_NAME + ") values(?,?,?)";

	@Override
	public User getUserById(int userId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		User user = new User();
		Tariff tariff = new Tariff();
		try {
			connection = getConnection();
			// not transaction?
			// connection.setAutoCommit(false);
			statement = connection.prepareStatement(GET_USER_BY_ID);
			statement.setInt(1, userId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt(UsersTable.ID));
				user.setAccount(rs.getString(UsersTable.ACCOUNT));
				user.setHashPassword(rs.getString(UsersTable.PASSWORD));
				user.setEmail(rs.getString(UsersTable.EMAIL));
				// convert to bigdecimal?
				user.setPhone(rs.getString(UsersTable.PHONE));
				// convert to bigdecimal?
				user.setAccountBalance(rs.getBigDecimal(UsersTable.ACCOUNT_BALANCE));
				Role role = Role.valueOf(rs.getString(RolesTable.NAME).toUpperCase());
				user.setRole(role);
				user.setBlocked(rs.getBoolean(UsersTable.BLOCKED));
				user.setDraft(rs.getBoolean(UsersTable.DRAFT));
				user.setFirstName(rs.getString(UsersTable.FIRST_NAME));
				user.setSecondName(rs.getString(UsersTable.SECOND_NAME));
				// mb use get tariff dao method?
				if (rs.getString(UsersTable.TARIFF) != null) {
					tariff.setId(Integer.valueOf(rs.getString(TariffsTable.ID)));
					tariff.setName(rs.getString(TariffsTable.NAME));
					tariff.setPrice(new BigDecimal(rs.getString(TariffsTable.PRICE)));
					tariff.setSpeed(Integer.valueOf(rs.getString(TariffsTable.SPEED)));
					tariff.setTraffic(Integer.valueOf(rs.getString(TariffsTable.TRAFFIC)));
					// boolean ok here? values 1 or 0
					tariff.setInactive(Boolean.valueOf(rs.getString(TariffsTable.INACTIVE)));
					user.setTariff(tariff);
				}
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection, statement);
		}
		return user;
	}

	@Override
	public User getUserByAccount(String accountNumber) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		User user = new User();
		Tariff tariff = new Tariff();
		try {
			connection = getConnection();
			// not transaction?
			// connection.setAutoCommit(false);
			statement = connection.prepareStatement(GET_USER_BY_ACCOUNT);
			statement.setString(1, accountNumber);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				user.setId(rs.getInt(UsersTable.ID));
				user.setAccount(rs.getString(UsersTable.ACCOUNT));
				user.setHashPassword(rs.getString(UsersTable.PASSWORD));
				user.setEmail(rs.getString(UsersTable.EMAIL));
				user.setPhone(rs.getString(UsersTable.PHONE));
				// convert to bigdecimal?
				user.setAccountBalance(rs.getBigDecimal(UsersTable.ACCOUNT_BALANCE));
				Role role = Role.valueOf(rs.getString(RolesTable.NAME).toUpperCase());
				user.setRole(role);
				user.setBlocked(rs.getBoolean(UsersTable.BLOCKED));
				user.setDraft(rs.getBoolean(UsersTable.DRAFT));
				user.setFirstName(rs.getString(UsersTable.FIRST_NAME));
				user.setSecondName(rs.getString(UsersTable.SECOND_NAME));
				// mb use get tariff dao method?
				if (rs.getString(UsersTable.TARIFF) != null) {
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
		} catch (SQLException  e) {
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection, statement);
		}
		return user;
	}

	@Override
	public void subscribeTariff(int userId, int newTariffId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			// not transaction?
			// connection.setAutoCommit(false);
			statement = connection.prepareStatement(SUBSCRIBE_FOR_TARIFF);
			statement.setInt(1, newTariffId);
			statement.setInt(2, userId);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection, statement);
		}
	}

	@Override
	public void setPassword(int userId, int hashPassword) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnection();
			savePassword(connection, userId, hashPassword);
		} finally {
			closeStatementsAndReleaseConnection(connection);
		}
	}

	@Override
	public void setContacts(int userId, String phone, String email) throws DAOException {
		Connection connection = null;
		try {
			connection = getConnection();
			saveContacts(connection, userId, phone, email);
		} finally {
			closeStatementsAndReleaseConnection(connection);
		}
	}

	@Override
	public List<User> showUsers() throws DAOException {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = getConnection();
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
				user.setHashPassword(rs.getString(UsersTable.PASSWORD));
				Role role = Role.valueOf(rs.getString(RolesTable.NAME).toUpperCase());
				user.setRole(role);
				user.setAccountBalance(rs.getBigDecimal((UsersTable.ACCOUNT_BALANCE)));
				user.setBlocked(rs.getBoolean(UsersTable.BLOCKED));
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
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection, statement);
		}
	}

	@Override
	public void pay(int userId, CreditCard card, BigDecimal amount) throws DAOException {
		Connection connection = null;
		PreparedStatement getCardStatement = null;
		PreparedStatement accountBalanceStatement = null;
		PreparedStatement cardBalanceStatement = null;
		try {
			connection = getConnection();
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
			} else {
				// smthg else
				throw new DAOException();
			}
		} catch (SQLException e) {
			rollbackConnection(e, connection);
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection, getCardStatement,
			        accountBalanceStatement, cardBalanceStatement);

		}
	}

	@Override
	public void register(int userId, int hashPassword, String phone, String email)
	        throws DAOException {
		Connection connection = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			savePassword(connection, userId, hashPassword);
			saveContacts(connection, userId, phone, email);
			connection.commit();
		} catch (SQLException e) {
			rollbackConnection(e, connection);
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection);
		}
	}

	private void savePassword(Connection connection, int userId, int hashPassword)
	        throws DAOException {
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(SAVE_PASSWORD);
			statement.setInt(1, hashPassword);
			statement.setInt(2, userId);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection, statement);
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
			closeStatementsAndReleaseConnection(connection, statement);
		}
	}

	@Override
	public void saveContract(String contract, String firstName, String secondName)
	        throws DAOException {
		Connection connection = null;
		PreparedStatement getUserStatement = null;
		PreparedStatement addContractStatement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			getUserStatement = connection.prepareStatement(GET_USER_BY_ACCOUNT);
			getUserStatement.setString(1, contract);
			ResultSet rs = getUserStatement.executeQuery();
			if (!rs.next()) {
				addContractStatement = connection.prepareStatement(SAVE_NEW_CONTRACT);
				addContractStatement.setString(1, contract);
				addContractStatement.setString(2, firstName);
				addContractStatement.setString(3, secondName);
				addContractStatement.executeUpdate();
				connection.commit();
			} else {
				// do smthg else!!
				throw new DAOException();
			}
		} catch (SQLException e) {
			rollbackConnection(e, connection);
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection, addContractStatement, getUserStatement);
		}

	}

	@Override
	public void saveUser(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement updateUserStatement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			updateUserStatement = connection.prepareStatement(UPDATE_USER);
			updateUserStatement.setString(1, user.getAccount());
			updateUserStatement.setString(2, user.getFirstName());
			updateUserStatement.setString(3, user.getSecondName());
			// updateUserStatement.setInt(4, user.getRole().getId());
			updateUserStatement.setInt(4, user.getTariff().getId());
			updateUserStatement.setBoolean(5, user.isBlocked());
			updateUserStatement.setInt(6, user.getId());
			updateUserStatement.executeUpdate();
			saveContacts(connection, user.getId(), user.getPhone(), user.getEmail());
			connection.commit();
		} catch (SQLException e) {
			rollbackConnection(e, connection);
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection, updateUserStatement);
		}
	}

	@Override
	public void applyDailyFee() throws DAOException {
		Connection connection = null;
		PreparedStatement setBalanceStatement = null;
		PreparedStatement blockStatement = null;
		PreparedStatement unblockStatement = null;
		List<User> usersList = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			usersList = showUsers();
			for (User user : usersList) {
				BigDecimal balance = user.getAccountBalance();
				BigDecimal tariffDaylyFee = user.getTariff().getPrice().divide(new BigDecimal(30),
				        2, RoundingMode.HALF_UP);
				if (balance.compareTo(tariffDaylyFee) >= 0) {
					setBalanceStatement = connection.prepareStatement(SET_BALANCE);
					setBalanceStatement.setBigDecimal(1, balance.subtract(tariffDaylyFee));
					setBalanceStatement.setInt(2, user.getId());
					setBalanceStatement.addBatch();
					if (user.isBlocked() == true) {
						unblockStatement = connection.prepareStatement(UNBLOCK_USER);
						unblockStatement.setInt(1, user.getId());
						unblockStatement.addBatch();
					}
				} else {
					blockStatement = connection.prepareStatement(BLOCK_USER);
					blockStatement.setInt(1, user.getId());
					blockStatement.addBatch();
				}
			}
			if (setBalanceStatement != null) {
				setBalanceStatement.executeBatch();
			}
			if (unblockStatement != null) {
				unblockStatement.executeBatch();
			}
			if (blockStatement != null) {
				blockStatement.executeBatch();
			}
			connection.commit();
		} catch (SQLException e) {
			rollbackConnection(e, connection);
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection, setBalanceStatement, 
					blockStatement, unblockStatement);
		}
	}
}
