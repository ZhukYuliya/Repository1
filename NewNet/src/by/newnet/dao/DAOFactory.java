package dao;

import dao.jdbc.RequestJdbcDAO;
import dao.jdbc.TariffJdbcDAO;
import dao.jdbc.UserJdbcDAO;

/**
 * A factory for creating DAO objects.
 */
public class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();
	
	private final UserDAO userDAO = new UserJdbcDAO();
	private final TariffDAO tariffDAO = new TariffJdbcDAO();
	private final RequestDAO requestDAO = new RequestJdbcDAO();

	
	/**
	 * Instantiates a new DAO factory.
	 */
	private DAOFactory(){}
	
	/**
	 * Gets the user DAO.
	 *
	 * @return the user DAO
	 */
	public UserDAO getUserDAO(){
		return userDAO;
	}
	
	/**
	 * Gets the tariff DAO.
	 *
	 * @return the tariff DAO
	 */
	public TariffDAO getTariffDAO(){
		return tariffDAO;
	}
	
	/**
	 * Gets the request DAO.
	 *
	 * @return the request DAO
	 */
	public RequestDAO getRequestDAO(){
		return requestDAO;
	}
	
	/**
	 * Gets the single instance of DAOFactory.
	 *
	 * @return single instance of DAOFactory
	 */
	public static DAOFactory getInstance(){
		return instance;
	}

}