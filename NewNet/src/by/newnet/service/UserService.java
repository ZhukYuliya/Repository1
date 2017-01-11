package by.newnet.service;

import java.util.List;

import by.newnet.domain.User;
import by.newnet.service.exception.ServiceException;

public interface UserService {
	// method name should be a verb?
	User authenticate(String account, String password) throws ServiceException;
	void register(User user) throws ServiceException;
	
	void subscribeTariff(int userId, int newTariffId) throws ServiceException;

	User getUser(int userId) throws ServiceException;
	void setPassword(int userId, String oldPassword, String newPassword) throws ServiceException;
	List<User> showUsers() throws ServiceException;

}
