package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class ShowAccountCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		UserService userService = ServiceFactory.getInstance().getUserService();
		User user = (User) request.getSession().getAttribute(RequestConstants.USER);
		int userId = user.getId();
		try {
			user = userService.getUserById(userId);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		request.setAttribute(RequestConstants.USER, user);
		// what to do for operator , admin?
		String page = null;
		if(user.isAdmin()){
			page = PageNames.ADMIN;
		} else if (user.isOperator()){
			page = PageNames.OPERATOR;
		} else {
			page = PageNames.HOME;
		}
		return page;
	}
}
