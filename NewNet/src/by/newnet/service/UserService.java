package by.newnet.service;

import by.newnet.domain.User;
import by.newnet.service.exception.ServiceException;

public interface UserService {
	
	User logination(User user) throws ServiceException;
	void registration(User user) throws ServiceException;

}
