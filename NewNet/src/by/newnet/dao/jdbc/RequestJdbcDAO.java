package by.newnet.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.newnet.dao.RequestDAO;
import by.newnet.dao.exception.DAOException;
import by.newnet.dao.jdbc.constant.RequestsTable;
import by.newnet.model.Request;
import by.newnet.model.RequestStatus;

/**
 * The Class RequestJdbcDAO.
 */
public class RequestJdbcDAO extends BaseJdbcDAO implements RequestDAO {
	public static final String POST_REQUEST = "INSERT INTO " + RequestsTable.REQUESTS + " ("
	        + RequestsTable.FIRST_NAME + "," + RequestsTable.EMAIL + "," + RequestsTable.PHONE + ","
	        + RequestsTable.ADDRESS + ") values(?, ?, ?, ?)";
	public static final String SHOW_REQUESTS = "SELECT * FROM " + RequestsTable.REQUESTS;
	public static final String SAVE_STATUS = "UPDATE " + RequestsTable.REQUESTS + " SET "
	        + RequestsTable.STATUS + "=? WHERE " + RequestsTable.ID + "=?";

	@Override
	public void postRequest(Request clientRequest) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(POST_REQUEST);
			statement.setString(1, clientRequest.getFirstName());
			statement.setString(2, clientRequest.getEmail());
			statement.setString(3, clientRequest.getPhone());
			statement.setString(4, clientRequest.getAddress());
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection, statement);
		}
	}

	@Override
	public List<Request> showRequests() throws DAOException {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(SHOW_REQUESTS);
			List<Request> requestsList = new ArrayList<Request>();
			while (rs.next()) {
				Request clientRequest = new Request();
				clientRequest.setId(rs.getInt(RequestsTable.ID));
				clientRequest.setFirstName(rs.getString(RequestsTable.FIRST_NAME));
				clientRequest.setEmail(rs.getString(RequestsTable.EMAIL));
				clientRequest.setPhone(rs.getString(RequestsTable.PHONE));
				clientRequest.setAddress(rs.getString(RequestsTable.ADDRESS));
				clientRequest
				        .setStatus(RequestStatus.valueOf((rs.getString(RequestsTable.STATUS))));
				requestsList.add(clientRequest);
			}
			return requestsList;
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection, statement);
		}
	}

	@Override
	public void setStatus(int requestId, RequestStatus status) throws DAOException {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(SAVE_STATUS);
			statement.setString(1, status.toString());
			statement.setInt(2, requestId);
			statement.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			closeStatementsAndReleaseConnection(connection, statement);
		}
	}

}
