package by.newnet.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.Tariff;
import by.newnet.domain.User;
import by.newnet.service.TariffService;
import by.newnet.service.UserService;
import by.newnet.service.ServiceFactory;
import by.newnet.service.exception.ServiceException;

public class ShowUsers implements Command {

		public static final String USERS_LIST = "tariffsList";
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		UserService userService = ServiceFactory.getInstance().getUserService();
		List<User> usersList = null;
		try {
			usersList = userService.showUsers();
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		request.setAttribute(USERS_LIST, usersList);
	
		return PageNames.USERS;
	}

}
