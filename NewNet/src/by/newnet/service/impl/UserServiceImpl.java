package by.newnet.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import by.newnet.dao.DAOFactory;
import by.newnet.dao.UserDAO;
import by.newnet.dao.exception.DAOException;
import by.newnet.model.CreditCard;
import by.newnet.model.User;
import by.newnet.model.UserComparator;
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
			if (loggedUser != null 
					&& (String.valueOf(password.hashCode()).equals(loggedUser.getHashPassword()))) {
				return loggedUser;
			} else {
				throw new ServiceAuthorizationException();
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

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
		if (!user.getHashPassword().equals(String.valueOf(oldPassword.hashCode()))) {
			throw new ServiceAuthorizationException();
		}
		try {
			userDAO.setPassword(userId, newPassword.hashCode());
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void setContacts(int userId, String phone, String email) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		/*
		 * User user = null; try { user = userDAO.getUserById(userId); } catch
		 * (DAOException e) { throw new ServiceException(e); }
		 */
		try {
			userDAO.setContacts(userId, phone, email);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public Pair<List<User>, Integer> showUsers(int page, int size) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		try {
			return userDAO.showUsers(page, size);
			//usersList.sort(new UserComparator());
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
	public User getUserForRegistration(String account) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		User user = null;
		try {
			user = userDAO.getUserByAccount(account);
			if(user !=null && !user.isDraft()){
				throw new UserAlreadyExistingException();
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user;
	}

	@Override
	public void register(int userId, String password, String phone, String email)
	        throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		try {
			userDAO.register(userId, password.hashCode(), phone, email);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	/*
	 * @Override public List<String> getAccountInfo(int userId) throws
	 * ServiceException { // TODO Auto-generated method stub return null; }
	 */

	@Override
	public void saveContract(String contract, String firstName, String secondName)
	        throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		try {
			userDAO.saveContract(contract, firstName, secondName);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void saveUser(User user) throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		try {
			userDAO.saveUser(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void applyDailyFee() throws ServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		try {
			userDAO.applyDailyFee();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
