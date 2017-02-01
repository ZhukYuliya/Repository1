package by.newnet.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import by.newnet.dao.exception.DAOException;
import by.newnet.dao.exception.FailedPaymentDAOException;
import by.newnet.model.CreditCard;
import by.newnet.model.User;

/**
 * The Interface UserDAO.
 */
public interface UserDAO {
	
	/**
	 * Register.
	 *
	 * @param userId the user id
	 * @param hashPassword the hash password
	 * @param phone the phone
	 * @param email the email
	 * @throws DAOException the DAO exception
	 */
	void register(int userId, int hashPassword, String phone, String email) throws DAOException;
	
	/**
	 * Subscribe tariff.
	 *
	 * @param userId the user id
	 * @param newTariffId the new tariff id
	 * @throws DAOException the DAO exception
	 */
	void subscribeTariff(int userId, int newTariffId) throws DAOException;
	
	/**
	 * Gets the user by id.
	 *
	 * @param userId the user id
	 * @return the user by id
	 * @throws DAOException the DAO exception
	 */
	User getUserById (int userId) throws DAOException;
	
	/**
	 * Gets the user by account.
	 *
	 * @param accountNumber the account number
	 * @return the user by account
	 * @throws DAOException the DAO exception
	 */
	User getUserByAccount (String accountNumber) throws DAOException;
	
	/**
	 * Sets the password.
	 *
	 * @param userId the user id
	 * @param hashPassword the hash password
	 * @throws DAOException the DAO exception
	 */
	void setPassword(int userId, int hashPassword) throws DAOException;
	
	/**
	 * Sets the contacts.
	 *
	 * @param userId the user id
	 * @param phone the phone
	 * @param email the email
	 * @throws DAOException the DAO exception
	 */
	void setContacts(int userId, String phone, String email)throws DAOException;
	
	/**
	 * Apply daily fee.
	 *
	 * @throws DAOException the DAO exception
	 */
	void applyDailyFee()throws DAOException;
	
	/**
	 * Pay.
	 *
	 * @param userId the user id
	 * @param card the card
	 * @param amount the amount
	 * @throws DAOException the DAO exception
	 */
	void pay(int userId, CreditCard card, BigDecimal amount) throws FailedPaymentDAOException, DAOException;
	
	/**
	 * Show users.
	 *
	 * @param page the page
	 * @param size the size
	 * @return the pair
	 * @throws DAOException the DAO exception
	 */
	Pair<List<User>, Integer> showUsers(int page, int size) throws DAOException;
	
	/**
	 * Save contract.
	 *
	 * @param contract the contract
	 * @param firstName the first name
	 * @param secondName the second name
	 * @throws DAOException the DAO exception
	 */
	void saveContract(String contract, String firstName, String secondName) throws DAOException;
	
	/**
	 * Save user.
	 *
	 * @param user the user
	 * @throws DAOException the DAO exception
	 */
	void saveUser(User user) throws DAOException;
	
	/**
	 * Show users.
	 *
	 * @return the list
	 * @throws DAOException the DAO exception
	 */
	List<User> showUsers() throws DAOException;

}
