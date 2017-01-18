package by.newnet.dao;

import java.util.List;

import by.newnet.dao.exception.DAOException;
import by.newnet.domain.Request;

public interface RequestDAO {
	
	boolean postRequest(Request clientRequest) throws DAOException;
	List<Request> showRequests()throws DAOException;
	
}
