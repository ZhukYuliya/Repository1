package by.newnet.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import by.newnet.dao.exception.DAOException;
import by.newnet.model.CreditCard;
import by.newnet.model.User;

public interface UserDAO {
	
	void register(int userId, int hashPassword, String phone, String email) throws DAOException;
	void subscribeTariff(int userId, int newTariffId) throws DAOException;
	User getUserById (int userId) throws DAOException;
	User getUserByAccount (String accountNumber) throws DAOException;
	void setPassword(int userId, int hashPassword) throws DAOException;
	void setContacts(int userId, String phone, String email)throws DAOException;
	void applyDailyFee()throws DAOException;
	void pay(int userId, CreditCard card, BigDecimal amount) throws DAOException;
	Pair<List<User>, Integer> showUsers(int page, int size) throws DAOException;
	void saveContract(String contract, String firstName, String secondName) throws DAOException;
	void saveUser(User user) throws DAOException;
	List<User> showUsers() throws DAOException;

}
