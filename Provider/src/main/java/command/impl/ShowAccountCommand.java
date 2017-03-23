package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import command.constant.PageNames;
import command.constant.RequestConstants;
import command.exception.CommandException;
import controller.ControllerAction;
import controller.ControllerForward;
import model.User;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

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
