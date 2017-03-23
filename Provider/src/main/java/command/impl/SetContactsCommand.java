package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import command.constant.PageNames;
import command.constant.RequestConstants;
import command.exception.CommandException;
import command.validator.Validator;
import controller.ControllerAction;
import controller.ControllerSendRedirect;
import model.User;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

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
