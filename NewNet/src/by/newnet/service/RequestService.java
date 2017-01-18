package by.newnet.service;

import java.util.List;

import by.newnet.domain.Request;
import by.newnet.service.exception.ServiceException;

public interface RequestService {
	
	void postRequest(Request clientRequest) throws ServiceException;
	List<Request> showRequests()throws ServiceException;

}
