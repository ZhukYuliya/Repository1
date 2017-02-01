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
