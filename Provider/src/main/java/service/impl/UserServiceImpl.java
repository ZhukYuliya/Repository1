package service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import dao.DAOFactory;
import dao.UserDAO;
import dao.exception.DAOException;
import dao.exception.FailedPaymentDAOException;
import model.CreditCard;
import model.User;
import service.UserService;
import service.exception.FailedPaymentServiceException;
import service.exception.ServiceAuthenticationException;
import service.exception.ServiceException;
import service.exception.UserAlreadyExistingServiceException;

/**
 * The Class UserServiceImpl.
 */
public class UserServiceImpl implements UserService {

	@Override
	public User authenticate(String account, String password)
	        throws ServiceException, ServiceAuthenticationException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		User loggedUser = null;
		try {
			loggedUser = userDAO.getUserByAccount(account);
			if (loggedUser != null 
					&& (String.valueOf(password.hashCode()).equals(loggedUser.getHashPassword()))) {
				return loggedUser;
			} else {
				throw new ServiceAuthenticationException();
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
			throw new ServiceAuthenticationException();
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
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void pay(int userId, CreditCard card, BigDecimal amount) throws ServiceException, FailedPaymentServiceException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		try {
			userDAO.pay(userId, card, amount);
		} catch (FailedPaymentDAOException e) {
			throw new FailedPaymentServiceException(e.getMessage(), e);
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
	public User getUserForRegistration(String account) throws ServiceException, ServiceAuthenticationException {
		DAOFactory daoFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoFactory.getUserDAO();
		User user = null;
		try {
			user = userDAO.getUserByAccount(account);
			if(user !=null && !user.isDraft()){
				throw new UserAlreadyExistingServiceException();
			}
			if(user == null){
				throw new ServiceAuthenticationException();
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
