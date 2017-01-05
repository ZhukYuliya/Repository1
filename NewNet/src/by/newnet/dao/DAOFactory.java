package by.newnet.dao;

import by.newnet.dao.jdbc.TariffJdbcDAO;
import by.newnet.dao.jdbc.UserJdbcDAO;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	
	private UserDAO userDAO = new UserJdbcDAO();
	private TariffDAO bookDAO = new TariffJdbcDAO();
	
	private DAOFactory(){}
	
	public UserDAO getUserDAO(){
		return userDAO;
	}
	
	public TariffDAO getBookDAO(){
		return bookDAO;
	}
	
	public static DAOFactory getInstance(){
		return instance;
	}

}