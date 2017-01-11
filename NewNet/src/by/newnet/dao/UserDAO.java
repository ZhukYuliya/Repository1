package by.newnet.dao;

import java.util.List;

import by.newnet.dao.exception.DAOException;
import by.newnet.domain.User;

public interface UserDAO {
	
	//User checkAuthorisationData(User user) throws DAOException;
	boolean registerUser(User user) throws DAOException;
	void subscribeTariff(int userId, int newTariffId) throws DAOException;
	User getUserById (int userId) throws DAOException;
	User getUserByAccount (String accountNumber) throws DAOException;

	void setPassword(String newPassword) throws DAOException;
	List<User> showUsers()throws DAOException;

}
