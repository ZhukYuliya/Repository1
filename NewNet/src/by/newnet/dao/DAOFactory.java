package by.newnet.dao;

import by.newnet.dao.jdbc.RequestJdbcDAO;
import by.newnet.dao.jdbc.TariffJdbcDAO;
import by.newnet.dao.jdbc.UserJdbcDAO;

public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	
	private final UserDAO userDAO = new UserJdbcDAO();
	private final TariffDAO tariffDAO = new TariffJdbcDAO();
	private final RequestDAO requestDAO = new RequestJdbcDAO();

	
	private DAOFactory(){}
	
	public UserDAO getUserDAO(){
		return userDAO;
	}
	
	public TariffDAO getTariffDAO(){
		return tariffDAO;
	}
	public RequestDAO getRequestDAO(){
		return requestDAO;
	}
	
	public static DAOFactory getInstance(){
		return instance;
	}

}