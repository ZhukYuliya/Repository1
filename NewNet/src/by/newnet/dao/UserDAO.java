package by.newnet.dao;

import java.math.BigDecimal;
import java.util.List;

import by.newnet.dao.exception.DAOException;
import by.newnet.domain.CreditCard;
import by.newnet.domain.User;

public interface UserDAO {
	
	//User checkAuthorisationData(User user) throws DAOException;
	void register(String password, String reenterPassword, String phone, String email) throws DAOException;
	void subscribeTariff(int userId, int newTariffId) throws DAOException;
	User getUserById (int userId) throws DAOException;
	User getUserByAccount (String accountNumber) throws DAOException;

	void setPassword(String newPassword) throws DAOException;
	void setContacts(String phone, String email)throws DAOException;

	void pay(int userId, CreditCard card, BigDecimal amount) throws DAOException;

	List<User> showUsers() throws DAOException;
	void addContract(String contract, String firstName, String secondName) throws DAOException;

}
