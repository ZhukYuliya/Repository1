package by.newnet.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.Book;
import by.newnet.domain.User;
import by.newnet.service.TariffService;
import by.newnet.service.UserService;
import by.newnet.service.ServiceFactory;
import by.newnet.service.exception.ServiceException;

public class ShowRequests implements Command {

		public static final String REQUESTS_LIST = "requestsList";
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		//user service or create new service?
		UserService userService = ServiceFactory.getInstance().getUserService();
		List<User> requestList = null;
		try {
			requestList = userService.s;
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		request.setAttribute(CATALOGUE, catalogue);
	
		return PageNames.CATALOGUE;
	}

}