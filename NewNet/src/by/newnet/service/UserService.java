package by.newnet.service;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import by.newnet.model.CreditCard;
import by.newnet.model.User;
import by.newnet.service.exception.ServiceException;
import by.newnet.service.exception.UserAlreadyExistingException;

public interface UserService {
	// method name should be a verb?
	User authenticate(String account, String password) throws ServiceException;
	User getUserForRegistration(String account) throws UserAlreadyExistingException, ServiceException;

	User getUserByAccount(String account) throws ServiceException;
	void register(int userId, String password, String phone, String email) throws ServiceException;
	
	void subscribeTariff(int userId, int newTariffId) throws ServiceException;

	User getUserById(int userId) throws ServiceException;
	void setPassword(int userId, String oldPassword, String newPassword) throws ServiceException;
	void setContacts(int userId, String phone, String email) throws ServiceException;

	void pay(int userId, CreditCard card, BigDecimal amount) throws ServiceException;
	//List<String> getAccountInfo(int userId) throws ServiceException;
	Pair<List<User>, Integer> showUsers(int page, int size) throws ServiceException;
	void saveContract(String contract, String firstName, String secondName)  throws ServiceException;
	void saveUser(User user)  throws ServiceException;
	void applyDailyFee() throws ServiceException;

}
