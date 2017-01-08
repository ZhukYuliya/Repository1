package by.newnet.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.newnet.dao.TariffDAO;
import by.newnet.dao.exception.DAOException;
import by.newnet.dao.jdbc.pool.ConnectionPool;
import by.newnet.dao.jdbc.pool.ConnectionPoolException;
import by.newnet.domain.Book;
import by.newnet.domain.Genre;
import by.newnet.domain.Tariff;

public class TariffJdbcDAO implements TariffDAO {
	public static final String SHOW_TARIFFS = "select * from tariffs";
	public static final String ADD_GENRE = "insert into genres (" + AccountsTable.NAME + ") values (?)";
	public static final String ADD_BOOK =
	        "insert into books (" + TariffsTable.DESCRIPTION + "," + TariffsTable.GENRE + ") values (?, ?)";
	public static final String CHECK_GENRE =
	        "select * from genres where " + AccountsTable.NAME + " = ? ";

	@Override
	public List<Tariff> showTariffs() throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			statement = connection.prepareStatement(SHOW_TARIFFS);
			ResultSet rs = statement.executeQuery();
			List<Tariff> tariffsList = new ArrayList<Tariff>();
			while (rs.next()) {
				Tariff tariff = new Tariff();
				tariff.setId(rs.getInt(TariffsTable.ID));
				tariff.setName(rs.getString(TariffsTable.NAME));
				tariff.setPrice(rs.getBigDecimal(TariffsTable.PRICE));
				tariff.setSpeed(rs.getInt(TariffsTable.SPEED));
				tariff.setTraffic(rs.getInt(TariffsTable.TRAFFIC));
				tariff.setInactive(rs.getBoolean(TariffsTable.INACTIVE));
				tariffsList.add(tariff);
			}
			return tariffsList;
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
	public void addTariff(Tariff tariff) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		PreparedStatement statement2 = null;
		PreparedStatement statement3 = null;
		PreparedStatement statement4 = null;
		try {
			connection = ConnectionPool.getInstance().takeConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(CHECK_GENRE);
			statement.setString(1, book.getGenre().getName());
			ResultSet rs = statement.executeQuery();
			Genre currentGenre = new Genre();
			if (rs.next()) {
				currentGenre.setId(rs.getInt(AccountsTable.ID));
			} else {
				statement2 = connection.prepareStatement(ADD_GENRE);
				statement2.setString(1, book.getGenre().getName());
				statement2.executeUpdate();
				statement3 = connection.prepareStatement(CHECK_GENRE);
				statement3.setString(1, book.getGenre().getName());
				ResultSet rs3 = statement.executeQuery();
				rs3.next();
				currentGenre.setId(rs3.getInt(AccountsTable.ID));
			}
			statement4 = connection.prepareStatement(ADD_BOOK);
			statement4.setString(1, book.getDescription());
			statement4.setInt(2, currentGenre.getId());
			statement4.executeUpdate();
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
					if (statement3 != null) {
						statement3.close();
						if (statement3 != null) {
							statement3.close();
						}
					}
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
