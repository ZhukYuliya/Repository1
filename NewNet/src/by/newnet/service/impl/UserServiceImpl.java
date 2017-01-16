package by.newnet.service.impl;

import java.math.BigDecimal;
import java.util.List;

import by.newnet.dao.DAOFactory;
import by.newnet.dao.TariffDAO;
import by.newnet.dao.UserDAO;
import by.newnet.dao.exception.DAOException;
import by.newnet.domain.CreditCard;
import by.newnet.domain.Request;
import by.newnet.domain.Tariff;
import by.newnet.domain.User;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceAuthorizationException;
import by.newnet.service.exception.ServiceException;
import by.newnet.service.exception.UserAlreadyExistingException;

public class UserServiceImpl implements UserService {

	@Override
	public User authenticate(String account, String password)
	        throws ServiceException, ServiceAuthorizationException {
		// why check on not null, shouldnt it be in command?
		/*
		 * if (!validation(user.getAccount(), user.getPassword())) { throw new
		 * ServiceAuthorizationException(); }
		 */
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		User loggedUser = null;
		// ok to return null here??
		try {
			loggedUser = userDAO.getUserByAccount(account);
			if (password.equals(loggedUser.getPassword())) {
				return loggedUser;
			}
			return null;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	// change validation!
	private boolean validation(String login, String password) {
		if (login.isEmpty() || login == null) {
			return false;
		}

		if (password.isEmpty() || password == null) {
			return false;
		}

		return true;
	}
/*
	@Override
	public void register(User user) throws ServiceException {

		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();

		try {
			boolean isRegistered = userDAO.registerUser(user);
			if (!isRegistered) {
				throw new UserAlreadyExistingException();
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}
*/
	@Override
	public void subscribeTariff(int userId, int newTariffId) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		try {
			userDAO.subscribeTariff(userId, newTariffId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User getUserById(int userId) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		User user = null;
		try {
			user = userDAO.getUserById(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public void setPassword(int userId, String oldPassword, String newPassword)
	        throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		User user = null;
		try {
			user = userDAO.getUserById(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		if (!user.getPassword().equals(oldPassword)) {
			throw new ServiceAuthorizationException();
		}
		try {
			userDAO.setPassword(newPassword);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}
	
	@Override
	public void setContacts(int userId, String phone, String email)
	        throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		User user = null;
		try {
			user = userDAO.getUserById(userId);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		try {
			userDAO.setContacts(phone, email);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<User> showUsers() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		try {
			List<User> usersList = userDAO.showUsers();
			return usersList;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void pay(int userId, CreditCard card, BigDecimal amount) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		try {
			userDAO.pay(userId, card, amount);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User getUserByAccount(String account) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		User user = null;
		try {
			user = userDAO.getUserByAccount(account);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public void register(String password, String reenterPassword, String phone, String email)
	        throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		try {
			userDAO.register(password, reenterPassword, phone, email);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public List<String> getAccountInfo(int userId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addContract(String contract, String firstName, String secondName)
	        throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		try {
			userDAO.addContract(contract, firstName, secondName);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}		
	}

}
