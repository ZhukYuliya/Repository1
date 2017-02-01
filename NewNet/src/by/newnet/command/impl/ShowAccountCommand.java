package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.constant.PageNames;
import by.newnet.command.constant.RequestConstants;
import by.newnet.command.exception.CommandException;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerForward;
import by.newnet.model.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

/**
 * The Class ShowAccountCommand. Shows the home page with account details and 
 * options to do for each user depending on his role.
 */
public class ShowAccountCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
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
		String page = null;
		if(user.isAdmin()){
			page = PageNames.ADMIN;
		} else if (user.isOperator()){
			page = PageNames.OPERATOR;
		} else {
			page = PageNames.HOME;
		}
		return new ControllerForward(page);
	}
}
