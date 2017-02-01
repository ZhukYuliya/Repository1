package by.newnet.service;

import by.newnet.service.impl.RequestServiceImpl;
import by.newnet.service.impl.TariffServiceImpl;
import by.newnet.service.impl.UserServiceImpl;

/**
 * A factory for creating Service objects.
 */
public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	
	private final UserService userService = new UserServiceImpl();
	private final TariffService tariffService = new TariffServiceImpl();
	private final RequestService requestService = new RequestServiceImpl();


		/**
		 * Instantiates a new service factory.
		 */
		private ServiceFactory(){}
	
	/**
	 * Gets the user service.
	 *
	 * @return the user service
	 */
	public UserService getUserService(){
		return userService;
	}
	
	/**
	 * Gets the tariff service.
	 *
	 * @return the tariff service
	 */
	public TariffService getTariffService(){
		return tariffService;
	}
	
	/**
	 * Gets the request service.
	 *
	 * @return the request service
	 */
	public RequestService getRequestService(){
		return requestService;
	}

	/**
	 * Gets the single instance of ServiceFactory.
	 *
	 * @return single instance of ServiceFactory
	 */
	public static ServiceFactory getInstance(){
		return instance;
	}

}
