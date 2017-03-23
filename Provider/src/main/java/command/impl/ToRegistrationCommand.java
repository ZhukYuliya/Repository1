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
 * The Class ToRegistrationCommand. Forwardes a guest to registration page after extracting his 
 * personal data.
 */
public class ToRegistrationCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		int userId = Integer.valueOf(request.getParameter(RequestConstants.USER_ID));
		UserService userService = ServiceFactory.getInstance().getUserService();
		User user = null;
		try {
			user = userService.getUserById(userId);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		request.setAttribute(RequestConstants.USER, user);
		return new ControllerForward(PageNames.REGISTRATION);
	}
}
