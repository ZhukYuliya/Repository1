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

import org.apache.commons.lang3.tuple.Pair;

import by.newnet.dao.UserDAO;
import by.newnet.dao.exception.DAOException;
import by.newnet.dao.jdbc.constant.CardsTable;
import by.newnet.dao.jdbc.constant.RolesTable;
import by.newnet.dao.jdbc.constant.TariffsTable;
import by.newnet.dao.jdbc.constant.UsersTable;
import by.newnet.model.CreditCard;
import by.newnet.model.Role;
import by.newnet.model.Tariff;
import by.newnet.model.User;

public class UserJdbcDAO extends BaseJdbcDAO implements UserDAO {
	public static final String CHECK_CREDENTIALS = "SELECT * FROM " + UsersTable.USERS + " WHERE "
	        + UsersTable.ACCOUNT + " = ? AND " + UsersTable.PASSWORD + " = ?";
	public static final String CHECK_USER = "SELECT " + UsersTable.ACCOUNT + " FROM "
	        + UsersTable.USERS + " WHERE " + UsersTable.ACCOUNT + " = ? ";
	public static final String SUBSCRIBE_FOR_TARIFF = "UPDATE " + UsersTable.USERS + " SET "
	        + UsersTable.TARIFF + " = ? WHERE " + UsersTable.ID + " = ?";
	public static final String GET_USER_BY_ID = "SELECT * FROM " + UsersTable.USERS + " LEFT JOIN "
	        + RolesTable.ROLES + " ON " + UsersTable.USERS + "." + UsersTable.ROLE + " = "
	        + RolesTable.ROLES + "." + RolesTable.ID + " left JOIN " + TariffsTable.TARIFFS + " ON "
	        + UsersTable.USERS + "." + UsersTable.TARIFF + " = " + TariffsTable.TARIFFS + "."
	        + TariffsTable.ID + " WHERE " + UsersTable.ID + " = ?";
	public static final String GET_USER_BY_ACCOUNT = "SELECT * FROM " + UsersTable.USERS
	        + " LEFT JOIN " + RolesTable.ROLES + " ON " + UsersTable.USERS + "." + UsersTable.ROLE
	        + " = " + RolesTable.ROLES + "." + RolesTable.ID + " LEFT JOIN " + TariffsTable.TARIFFS
	        + " ON " + UsersTable.USERS + "." + UsersTable.TARIFF + " = " + TariffsTable.TARIFFS
	        + "." + TariffsTable.ID + " WHERE " + UsersTable.ACCOUNT + " = ?";
	public static final String SAVE_PASSWORD = "UPDATE " + UsersTable.USERS + " SET "
	        + UsersTable.PASSWORD + "=? WHERE " + UsersTable.ID + "=?";
	public static final String SET_NOT_DRAFT = "UPDATE " + UsersTable.USERS + " SET "
	        + UsersTable.DRAFT + "=false WHERE " + UsersTable.ID + "=?";
	public static final String SET_BALANCE = "UPDATE " + UsersTable.USERS + " SET "
	        + UsersTable.ACCOUNT_BALANCE + "=? WHERE " + UsersTable.ID + "=?";
	public static final String SAVE_CONTACTS = "UPDATE " + UsersTable.USERS + " SET "
	        + UsersTable.PHONE + "=?, " + UsersTable.EMAIL + "=? WHERE " + UsersTable.ID + "=?";
	public static final String BLOCK_USER = "UPDATE " + UsersTable.USERS + " SET "
	        + UsersTable.BLOCKED + "=true WHERE " + UsersTable.ID + "=?";
	public static final String UNBLOCK_USER = "UPDATE " + UsersTable.USERS + " SET "
	        + UsersTable.BLOCKED + "=false WHERE " + UsersTable.ID + "=?";
	public static final String UPDATE_USER = "UPDATE " + UsersTable.USERS + " SET "
	        + UsersTable.ACCOUNT + "=?, " + UsersTable.FIRST_NAME + "=?, " + UsersTable.SECOND_NAME
	        + "=?, " + UsersTable.TARIFF + "=?, " + UsersTable.BLOCKED + "=? " + " WHERE "
	        + UsersTable.ID + "=?";
	public static final String COUNT_USERS = "SELECT COUNT(*)  FROM " + UsersTable.USERS;
	public static final String SHOW_USERS_PAGINATED = "SELECT *  FROM " + UsersTable.USERS
	        + " JOIN " + RolesTable.ROLES + " ON " + UsersTable.USERS + "." + UsersTable.ROLE
	        + " = " + RolesTable.ROLES + "." + RolesTable.ID + " JOIN " + TariffsTable.TARIFFS
	        + " ON " + UsersTable.USERS + "." + UsersTable.TARIFF + " = " + TariffsTable.TARIFFS
	        + "." + TariffsTable.ID + " ORDER BY " + UsersTable.ID + " LIMIT ?,?";
	public static final String SHOW_USERS = "SELECT *  FROM " + UsersTable.USERS + " JOIN "
	        + RolesTable.ROLES + " ON " + UsersTable.USERS + "." + UsersTable.ROLE + " = "
	        + RolesTable.ROLES + "." + RolesTable.ID + " JOIN " + TariffsTable.TARIFFS + " ON "
	        + UsersTable.USERS + "." + UsersTable.TARIFF + " = " + TariffsTable.TARIFFS + "."
	        + TariffsTable.ID;
	public static final String GET_CARD_BY_NUMBER =
	        "SELECT * FROM " + CardsTable.CARDS + " WHERE " + CardsTable.NUMBER + " = ?";
	public static final String SET_ACCOUNT_BALANCE = "UPDATE " + UsersTable.USERS + " SET "
	        + UsersTable.ACCOUNT_BALANCE + "=? WHERE " + UsersTable.ACCOUNT + "=?";
	public static final String SET_CARD_BALANCE = "UPDATE " + CardsTable.CARDS + " SET "
	        + CardsTable.BALANCE + "=? WHERE " + CardsTable.NUMBER + "=?";
	public static final String SAVE_NEW_CONTRACT =
	        "INSERT INTO " + UsersTable.USERS + " (" + UsersTable.ACCOUNT + ","
	                + UsersTable.FIRST_NAME + "," + UsersTable.SECOND_NAME + ") VALUES(?,?,?)";

	@Override
	public User getUserById(int userId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		User user = null;
		try {
			connection = getConnection();
			// not transaction?
			// connection.setAutoCommit(false);
			statement = connection.prepareStatement(GET_USER_BY_ID);
			statement.setInt(1, userId);
			ResultSet rs = statement.executeQuery();
			// while or if??
			while (rs.next()) {
				user = fillInCurrentUser(rs);
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
		User user = null;
		try {
			connection = getConnection();
			// not transaction?
			// connection.setAutoCommit(false);
			statement = connection.prepareStatement(GET_USER_BY_ACCOUNT);
			statement.setString(1, accountNumber);
			ResultSet rs = statement.executeQuery();
			// while or if?
			while (rs.next()) {
				user = fillInCurrentUser(rs);
				// what if user is not null but empty?
			}
		} catch (SQLException e) {
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
				User user = fillInCurrentUser(rs);
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
	public Pair<List<User>, Integer> showUsers(int page, int size) throws DAOException {
		Connection connection = null;
		Statement countStatement = null;
		PreparedStatement showUsersStatement = null;
		Pair<List<User>, Integer> usersCountPair = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			countStatement = connection.createStatement();
			ResultSet rs = countStatement.executeQuery(COUNT_USERS);
			rs.next();
			int count = rs.getInt(1);
			if (count > 0) {
				showUsersStatement = connection.prepareStatement(SHOW_USERS_PAGINATED);
				showUsersStatement.setInt(1, (page-1)*size);
				showUsersStatement.setInt(2, size);
				ResultSet usersRs = showUsersStatement.executeQuery();
				List<User> usersList = new ArrayList<User>();
				while (usersRs.next()) {
					User user = fillInCurrentUser(usersRs);
					usersList.add(user);
				}
				usersCountPair = Pair.of(usersList, count);
			} 
			connection.commit();
			return usersCountPair;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection, countStatement, showUsersStatement);
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
			PreparedStatement draftStatement = connection.prepareStatement(SET_NOT_DRAFT);
			draftStatement.setInt(1, userId);
			draftStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			rollbackConnection(e, connection);
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection);
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
					if (user.isBlocked()) {
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
			closeStatementsAndReleaseConnection(connection, setBalanceStatement, blockStatement,
			        unblockStatement);
		}
	}

	private User fillInCurrentUser(ResultSet rs) throws DAOException, SQLException {
		User user = new User();
		user.setId(rs.getInt(UsersTable.ID));
		user.setAccount(rs.getString(UsersTable.ACCOUNT));
		user.setHashPassword(rs.getString(UsersTable.PASSWORD));
		user.setEmail(rs.getString(UsersTable.EMAIL));
		user.setPhone(rs.getString(UsersTable.PHONE));
		user.setAccountBalance(rs.getBigDecimal(UsersTable.ACCOUNT_BALANCE));
		Role role = Role.valueOf(rs.getString(RolesTable.NAME).toUpperCase());
		user.setRole(role);
		user.setBlocked(rs.getBoolean(UsersTable.BLOCKED));
		user.setDraft(rs.getBoolean(UsersTable.DRAFT));
		user.setFirstName(rs.getString(UsersTable.FIRST_NAME));
		user.setSecondName(rs.getString(UsersTable.SECOND_NAME));
		// mb use get tariff dao method?
		if (rs.getString(UsersTable.TARIFF) != null) {
			Tariff tariff = new Tariff();
			tariff.setId(Integer.valueOf(rs.getString(TariffsTable.ID)));
			tariff.setName(rs.getString(TariffsTable.NAME));
			tariff.setPrice(new BigDecimal(rs.getString(TariffsTable.PRICE)));
			tariff.setSpeed(Integer.valueOf(rs.getString(TariffsTable.SPEED)));
			tariff.setTraffic(Integer.valueOf(rs.getString(TariffsTable.TRAFFIC)));
			// boolean ok here? values 1 or 0
			tariff.setInactive(Boolean.valueOf(rs.getString(TariffsTable.INACTIVE)));
			user.setTariff(tariff);
		}
		return user;
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
		}
	}
}
