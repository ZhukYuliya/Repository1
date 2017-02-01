package by.newnet.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import by.newnet.dao.exception.FailedPaymentDAOException;
import by.newnet.model.CreditCard;
import by.newnet.model.User;
import by.newnet.service.exception.FailedPaymentServiceException;
import by.newnet.service.exception.ServiceAuthenticationException;
import by.newnet.service.exception.ServiceException;
import by.newnet.service.exception.UserAlreadyExistingServiceException;

/**
 * The Interface UserService.
 */
public interface UserService {
	
	/**
	 * Authenticate.
	 *
	 * @param account the account
	 * @param password the password
	 * @return the user
	 * @throws ServiceException the service exception
	 */
	User authenticate(String account, String password) throws ServiceException;
	
	/**
	 * Gets the user for registration.
	 *
	 * @param account the account
	 * @return the user for registration
	 * @throws UserAlreadyExistingServiceException the user already existing exception
	 * @throws ServiceException the service exception
	 */
	User getUserForRegistration(String account) throws UserAlreadyExistingServiceException, ServiceAuthenticationException, ServiceException;

	/**
	 * Gets the user by account.
	 *
	 * @param account the account
	 * @return the user by account
	 * @throws ServiceException the service exception
	 */
	User getUserByAccount(String account) throws ServiceException;
	
	/**
	 * Register.
	 *
	 * @param userId the user id
	 * @param password the password
	 * @param phone the phone
	 * @param email the email
	 * @throws ServiceException the service exception
	 */
	void register(int userId, String password, String phone, String email) throws ServiceException;
	
	/**
	 * Subscribe tariff.
	 *
	 * @param userId the user id
	 * @param newTariffId the new tariff id
	 * @throws ServiceException the service exception
	 */
	void subscribeTariff(int userId, int newTariffId) throws ServiceException;

	/**
	 * Gets the user by id.
	 *
	 * @param userId the user id
	 * @return the user by id
	 * @throws ServiceException the service exception
	 */
	User getUserById(int userId) throws ServiceException;
	
	/**
	 * Sets the password.
	 *
	 * @param userId the user id
	 * @param oldPassword the old password
	 * @param newPassword the new password
	 * @throws ServiceException the service exception
	 */
	void setPassword(int userId, String oldPassword, String newPassword) throws ServiceException;
	
	/**
	 * Sets the contacts.
	 *
	 * @param userId the user id
	 * @param phone the phone
	 * @param email the email
	 * @throws ServiceException the service exception
	 */
	void setContacts(int userId, String phone, String email) throws ServiceException;

	/**
	 * Pay.
	 *
	 * @param userId the user id
	 * @param card the card
	 * @param amount the amount
	 * @throws ServiceException the service exception
	 * @throws FailedPaymentDAOException 
	 */
	void pay(int userId, CreditCard card, BigDecimal amount) throws ServiceException, FailedPaymentServiceException;
	
	/**
	 * Show users.
	 *
	 * @param page the page
	 * @param size the size
	 * @return the pair
	 * @throws ServiceException the service exception
	 */
	Pair<List<User>, Integer> showUsers(int page, int size) throws ServiceException;
	
	/**
	 * Save contract.
	 *
	 * @param contract the contract
	 * @param firstName the first name
	 * @param secondName the second name
	 * @throws ServiceException the service exception
	 */
	void saveContract(String contract, String firstName, String secondName)  throws ServiceException;
	
	/**
	 * Save user.
	 *
	 * @param user the user
	 * @throws ServiceException the service exception
	 */
	void saveUser(User user)  throws ServiceException;
	
	/**
	 * Apply daily fee.
	 *
	 * @throws ServiceException the service exception
	 */
	void applyDailyFee() throws ServiceException;

}
