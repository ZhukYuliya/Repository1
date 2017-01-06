package by.newnet.dao;

import java.util.List;

import by.newnet.dao.exception.DAOException;
import by.newnet.domain.User;

public interface UserDAO {
	
	User checkAuthorisationData(User user) throws DAOException;
	boolean registerUser(User user) throws DAOException;
	boolean postRequest(User user) throws DAOException;
	List<User> showRequests();


}
