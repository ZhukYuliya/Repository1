package by.newnet.service;

import by.newnet.service.impl.RequestServiceImpl;
import by.newnet.service.impl.TariffServiceImpl;
import by.newnet.service.impl.UserServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	
	private UserService userService = new UserServiceImpl();
	private TariffService tariffService = new TariffServiceImpl();
	private RequestService requestService = new RequestServiceImpl();


		private ServiceFactory(){}
	
	public UserService getUserService(){
		return userService;
	}
	
	public TariffService getTariffService(){
		return tariffService;
	}
	
	public RequestService getRequestService(){
		return requestService;
	}

	public static ServiceFactory getInstance(){
		return instance;
	}

}
