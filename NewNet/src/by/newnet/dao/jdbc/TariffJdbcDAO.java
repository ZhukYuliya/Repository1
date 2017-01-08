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
import by.newnet.domain.Tariff;

public class TariffJdbcDAO implements TariffDAO {
    public static final String SHOW_TARIFFS = "select * from tariffs";
    public static final String ADD_TARIFF = "insert into tariffs (" + TariffsTable.NAME + "," + TariffsTable.PRICE + ","
            + TariffsTable.SPEED + "," + TariffsTable.TRAFFIC + "," + TariffsTable.INACTIVE + ") values (?,?,?,?,?)";
    public static final String GET_TARIFF = "select * from tariffs where " + TariffsTable.NAME + " = ? ";

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
    public Tariff getTariff(String tariffName) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        Tariff tariff = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(GET_TARIFF);
            statement.setString(1, tariffName);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                tariff.setId(rs.getInt(TariffsTable.ID));
                tariff.setName(rs.getString(TariffsTable.NAME));
                tariff.setPrice(rs.getBigDecimal(TariffsTable.PRICE));
                tariff.setSpeed(rs.getInt(TariffsTable.SPEED));
                tariff.setTraffic(rs.getInt(TariffsTable.TRAFFIC));
                tariff.setInactive(rs.getBoolean(TariffsTable.INACTIVE));
            }
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
        return tariff;
    }

    @Override
    public void addTariff(Tariff tariff) throws DAOException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(ADD_TARIFF);
            statement.setString(1, tariff.getName());
            statement.setBigDecimal(2, tariff.getPrice());
            statement.setInt(3, tariff.getSpeed());
            statement.setInt(4, tariff.getTraffic());
            // bit boolean?
            statement.setBoolean(5, tariff.isInactive());
            statement.executeUpdate();
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
