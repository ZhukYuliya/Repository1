package by.newnet.service.impl;

import by.newnet.dao.DAOFactory;
import by.newnet.dao.UserDAO;
import by.newnet.dao.exception.DAOException;
import by.newnet.domain.User;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceAuthorizationException;
import by.newnet.service.exception.ServiceException;
import by.newnet.service.exception.UserAlreadyExistingException;

public class UserServiceImpl implements UserService{

	@Override
	public User logination(User user) throws ServiceException, ServiceAuthorizationException {
		
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

}
