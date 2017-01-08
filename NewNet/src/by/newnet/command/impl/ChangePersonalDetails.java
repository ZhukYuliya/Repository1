package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class ChangePersonalDetails implements Command {

		public static final String USER = "user";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		UserService userService = ServiceFactory.getInstance().getUserService();
		int userId = ((User)request.getSession().getAttribute(USER)).getId();
		User user = null;
		try {
			user = userService.getUser(userId);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		request.setAttribute(USER, user);
	
		return PageNames.PERSONAL_DETAILS;
	}

}
