package by.newnet.service;

import java.math.BigDecimal;
import java.util.List;

import by.newnet.domain.CreditCard;
import by.newnet.domain.User;
import by.newnet.service.exception.ServiceException;

public interface UserService {
	// method name should be a verb?
	User authenticate(String account, String password) throws ServiceException;
	User getUserByAccount(String account) throws ServiceException;
	void register(int userId, String password, String reenterPassword, String phone, String email) throws ServiceException;
	
	void subscribeTariff(int userId, int newTariffId) throws ServiceException;

	User getUserById(int userId) throws ServiceException;
	void setPassword(int userId, String oldPassword, String newPassword) throws ServiceException;
	void setContacts(int userId, String phone, String email) throws ServiceException;

	void pay(int userId, CreditCard card, BigDecimal amount) throws ServiceException;
	//List<String> getAccountInfo(int userId) throws ServiceException;
	List<User> showUsers() throws ServiceException;
	void addContract(String contract, String firstName, String secondName)  throws ServiceException;
	void saveUser(User user)  throws ServiceException;

}
