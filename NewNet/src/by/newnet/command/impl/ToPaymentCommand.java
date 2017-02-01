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
 * The Class ToPaymentCommand. Refreshes the information about user in session and
 * forwardes him to the page with a form payment.
 */
public class ToPaymentCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		UserService userService = ServiceFactory.getInstance().getUserService();
		int userId = ((User)request.getSession().getAttribute(RequestConstants.USER)).getId();
		User user = null;
		try {
			user = userService.getUserById(userId);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		request.setAttribute(RequestConstants.USER, user);
		return new ControllerForward(PageNames.PAYMENT);
	}

}
