package service;

import java.util.List;

import model.Request;
import model.RequestStatus;
import service.exception.ServiceException;

/**
 * The Interface RequestService.
 */
public interface RequestService {
	
	/**
	 * Post request.
	 *
	 * @param clientRequest the client request
	 * @throws ServiceException the service exception
	 */
	void postRequest(Request clientRequest) throws ServiceException;
	
	/**
	 * Show requests.
	 *
	 * @return the list
	 * @throws ServiceException the service exception
	 */
	List<Request> showRequests()throws ServiceException;
	
	/**
	 * Sets the status.
	 *
	 * @param requestId the request id
	 * @param status the status
	 * @throws ServiceException the service exception
	 */
	void setStatus(int requestId, RequestStatus status)throws ServiceException;

}
