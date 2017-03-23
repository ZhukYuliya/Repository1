package service.impl;

import java.util.Collections;
import java.util.List;

import dao.DAOFactory;
import dao.RequestDAO;
import dao.exception.DAOException;
import model.Request;
import model.RequestStatus;
import service.RequestService;
import service.exception.ServiceException;

/**
 * The Class RequestServiceImpl.
 */
public class RequestServiceImpl implements RequestService {

	@Override
	public void postRequest(Request clientRequest) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		RequestDAO requestDAO = daoFactory.getRequestDAO();

		try {
			requestDAO.postRequest(clientRequest);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Request> showRequests() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		RequestDAO requestDAO = daoFactory.getRequestDAO();

		try {
			List<Request> requestsList = requestDAO.showRequests();
			/*
			 * Sorts requests by their status so that the new requests appear first in the list.
			 */
			Collections.sort(requestsList);
			return requestsList;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void setStatus(int requestId, RequestStatus status) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		RequestDAO requestDAO = daoFactory.getRequestDAO();

		try {
			requestDAO.setStatus(requestId, status);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

}
