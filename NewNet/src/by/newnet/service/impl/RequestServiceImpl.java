package by.newnet.service.impl;

import java.util.Collections;
import java.util.List;

import by.newnet.dao.DAOFactory;
import by.newnet.dao.RequestDAO;
import by.newnet.dao.exception.DAOException;
import by.newnet.domain.Request;
import by.newnet.domain.RequestStatus;
import by.newnet.service.RequestService;
import by.newnet.service.exception.ServiceException;

public class RequestServiceImpl implements RequestService {

	@Override
	public void postRequest(Request clientRequest) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		RequestDAO requestDAO = daoFactory.getRequestDAO();

		try {
			boolean isPosted = requestDAO.postRequest(clientRequest);
			if (!isPosted) {
				// check on existing user AND existing order
				// throw new UserAlreadyExistingException();
			}
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
