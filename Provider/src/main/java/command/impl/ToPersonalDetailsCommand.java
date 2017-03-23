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
 * The Class ChangePersonalDetailsCommand. Refreshes the information about user in session and
 * forwardes him to the page with a form for changing personal details.
 */
public class ToPersonalDetailsCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		UserService userService = ServiceFactory.getInstance().getUserService();
		int userId = ((User)request.getSession().getAttribute(RequestConstants.USER)).getId();
		User user;
		try {
			user = userService.getUserById(userId);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		request.setAttribute(RequestConstants.USER, user);
		return new ControllerForward(PageNames.PERSONAL_DETAILS);
	}
}
