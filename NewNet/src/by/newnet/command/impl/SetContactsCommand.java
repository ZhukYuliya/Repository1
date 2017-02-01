package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.constant.PageNames;
import by.newnet.command.constant.RequestConstants;
import by.newnet.command.exception.CommandException;
import by.newnet.command.validator.Validator;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerSendRedirect;
import by.newnet.model.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

/**
 * The Class SetContactsCommand. Updates the email and phone of a user
 */
public class SetContactsCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String phone = request.getParameter(RequestConstants.PHONE);
		String email = request.getParameter(RequestConstants.EMAIL);

		int userId = ((User) request.getSession().getAttribute(RequestConstants.USER)).getId();
		String message = Validator.validateContacts(phone, email);
		if (message == null) {
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				userService.setContacts(userId, phone, email);
				message = RequestConstants.CONTACTS_UPDATED;
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		}
		/*
		 * Redirects the user to the same page saying either that the contacts were updated
		 * or that the contacts validation failed.
		 */
		request.setAttribute(RequestConstants.SET_CONTACTS_MESSAGE, message);
		return new ControllerSendRedirect(PageNames.TO_PERSONAL_DETAILS_COMMAND 
				+ "&" + RequestConstants.SET_CONTACTS_MESSAGE + "=" + message);
	}
}
