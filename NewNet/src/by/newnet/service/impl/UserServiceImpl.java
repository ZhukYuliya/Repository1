package by.newnet.service.impl;

import java.util.List;

import by.newnet.dao.DAOFactory;
import by.newnet.dao.UserDAO;
import by.newnet.dao.exception.DAOException;
import by.newnet.domain.Tariff;
import by.newnet.domain.User;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceAuthorizationException;
import by.newnet.service.exception.ServiceException;
import by.newnet.service.exception.UserAlreadyExistingException;

public class UserServiceImpl implements UserService{

	@Override
	public User authentication(User user) throws ServiceException, ServiceAuthorizationException {
		// why check on not null, shouldnt it be in command?
		if (!validation(user.getLogin(), user.getPassword())){
			throw new ServiceAuthorizationException();
		}
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		
		try {
			User loggedUser = userDAO.checkAuthorisationData(user);
			return loggedUser;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	private boolean validation(String login, String password){
		if (login.isEmpty() || login == null){
			return false;
		}
		
		if (password.isEmpty() || password == null){
			return false;
		}
		
		return true;
	}

	@Override
	public void registration(User user) throws ServiceException {
		
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		
		try {
			boolean isRegistered = userDAO.registerUser(user);
			if (!isRegistered){
				throw new UserAlreadyExistingException();
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	
	}

	@Override
	public void postRequest(User preCustomer) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		
		try {
			boolean isPosted = userDAO.postRequest(preCustomer);
			if (!isPosted){
				// check on existing user AND existing order
				//throw new UserAlreadyExistingException();
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}
	
	@Override
	public List<User> showRequests() throws ServiceException {
			DAOFactory daoFactory = DAOFactory.getInstance();
			UserDAO userDAO = daoFactory.getUserDAO();

			try {
				List<User> requestsList = userDAO.showRequests();
				return requestsList;
			} catch (DAOException e) {
				throw new ServiceException(e);
			}

		}
		
	}

}
