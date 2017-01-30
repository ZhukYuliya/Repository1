package by.newnet.dao;

import java.util.List;

import by.newnet.dao.exception.DAOException;
import by.newnet.model.Request;
import by.newnet.model.RequestStatus;

public interface RequestDAO {
	
	boolean postRequest(Request clientRequest) throws DAOException;
	List<Request> showRequests()throws DAOException;
	void setStatus(int requestId, RequestStatus status)throws DAOException;
	
}
