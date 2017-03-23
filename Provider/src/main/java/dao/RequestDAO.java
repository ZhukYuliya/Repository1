package dao;

import java.util.List;

import dao.exception.DAOException;
import model.Request;
import model.RequestStatus;

/**
 * The Interface RequestDAO.
 */
public interface RequestDAO {
	
	/**
	 * Post request.
	 *
	 * @param clientRequest the client request
	 * @return true, if successful
	 * @throws DAOException the DAO exception
	 */
	void postRequest(Request clientRequest) throws DAOException;
	
	/**
	 * Show requests.
	 *
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<Request> showRequests()throws DAOException;
	
	/**
	 * Sets the status.
	 *
	 * @param requestId the request id
	 * @param status the status
	 * @throws DAOException the DAO exception
	 */
	void setStatus(int requestId, RequestStatus status)throws DAOException;
	
}
