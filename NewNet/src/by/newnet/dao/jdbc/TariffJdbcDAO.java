package by.newnet.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.newnet.dao.TariffDAO;
import by.newnet.dao.exception.DAOException;
import by.newnet.dao.jdbc.constant.TariffsTable;
import by.newnet.domain.Tariff;

public class TariffJdbcDAO extends BaseJdbcDAO implements TariffDAO {
	public static final String SHOW_TARIFFS = "SELECT * FROM " + TariffsTable.TARIFFS;
	public static final String UPDATE_TARIFF = "UPDATE " + TariffsTable.TARIFFS + " SET "
	        + TariffsTable.NAME + "=?, " + TariffsTable.PRICE + "=?, " + TariffsTable.SPEED + "=?, "
	        + TariffsTable.TRAFFIC + "=?, " + TariffsTable.INACTIVE + "=?, WHERE" + TariffsTable.ID
	        + "=?";
	public static final String ADD_TARIFF = "INSERT INTO " + TariffsTable.TARIFFS + " (" + TariffsTable.NAME + ","
	        + TariffsTable.PRICE + "," + TariffsTable.SPEED + "," + TariffsTable.TRAFFIC + ","
	        + TariffsTable.INACTIVE + ") values (?,?,?,?,?)";
	public static final String GET_TARIFF_BY_NAME =
	        "SELECT * FROM " + TariffsTable.TARIFFS + " WHERE " + TariffsTable.NAME + " = ? ";
	public static final String GET_TARIFF_BY_ID =
	        "SELECT * FROM " + TariffsTable.TARIFFS + " WHERE " + TariffsTable.ID + " = ? ";

	@Override
	public List<Tariff> showTariffs() throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(SHOW_TARIFFS);
			ResultSet rs = statement.executeQuery();
			List<Tariff> tariffsList = new ArrayList<Tariff>();
			while (rs.next()) {
				Tariff tariff = fillInCurrentTariff(rs);
				tariffsList.add(tariff);
			}
			return tariffsList;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection, statement);
		}
	}

	@Override
	public Tariff getTariffByName(String tariffName) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		Tariff tariff = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(GET_TARIFF_BY_NAME);
			statement.setString(1, tariffName);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				tariff = fillInCurrentTariff(rs);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection, statement);
		}
		return tariff;
	}

	@Override
	public void addTariff(Tariff tariff) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(ADD_TARIFF);
			statement.setString(1, tariff.getName());
			statement.setBigDecimal(2, tariff.getPrice());
			statement.setInt(3, tariff.getSpeed());
			statement.setInt(4, tariff.getTraffic());
			// bit boolean?
			statement.setBoolean(5, tariff.isInactive());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection, statement);
		}

	}

	@Override
	public void updateTariff(Tariff tariff) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(UPDATE_TARIFF);
			statement.setString(1, tariff.getName());
			statement.setBigDecimal(2, tariff.getPrice());
			statement.setInt(3, tariff.getSpeed());
			statement.setInt(4, tariff.getTraffic());
			// bit boolean?
			statement.setBoolean(5, tariff.isInactive());
			statement.setInt(6, tariff.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection, statement);
		}

	}

	@Override
	public Tariff getTariffById(int tariffId) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		Tariff tariff = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(GET_TARIFF_BY_ID);
			statement.setInt(1, tariffId);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				tariff = fillInCurrentTariff(rs);
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection, statement);
		}
		return tariff;
	}

	private Tariff fillInCurrentTariff(ResultSet rs) throws DAOException, SQLException {
		Tariff tariff = new Tariff();
		tariff.setId(rs.getInt(TariffsTable.ID));
		tariff.setName(rs.getString(TariffsTable.NAME));
		tariff.setPrice(rs.getBigDecimal(TariffsTable.PRICE));
		tariff.setSpeed(rs.getInt(TariffsTable.SPEED));
		tariff.setTraffic(rs.getInt(TariffsTable.TRAFFIC));
		tariff.setInactive(rs.getBoolean(TariffsTable.INACTIVE));
		return tariff;
	}

}
