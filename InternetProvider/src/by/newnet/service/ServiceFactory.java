package by.newnet.service;

import by.newnet.service.impl.TariffServiceImpl;
import by.newnet.service.impl.UserServiceImpl;

public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();
	
	private UserService userService = new UserServiceImpl();
	private TariffService tariffService = new TariffServiceImpl();

		private ServiceFactory(){}
	
	public UserService getUserService(){
		return userService;
	}
	
	public TariffService getTariffService(){
		return tariffService;
	}
	
	public static ServiceFactory getInstance(){
		return instance;
	}

}
