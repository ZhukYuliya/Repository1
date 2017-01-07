package by.newnet.service;

import java.util.List;

import by.newnet.domain.User;
import by.newnet.service.exception.ServiceException;

public interface UserService {
	
	User authentication(User user) throws ServiceException;
	void registration(User user) throws ServiceException;
	void postRequest(User preCustomer) throws ServiceException;
	List<User> showRequests()throws ServiceException;

	void subscribeTariff(int newTariffId) throws ServiceException;

	User getUser(int userId) throws ServiceException;

}
