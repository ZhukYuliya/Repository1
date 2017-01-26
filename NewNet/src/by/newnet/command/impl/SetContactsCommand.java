package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerSendRedirect;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class SetContactsCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String phone;
		String email;

		phone = request.getParameter(RequestConstants.PHONE);
		email = request.getParameter(RequestConstants.EMAIL);

		int userId = ((User) request.getSession().getAttribute(RequestConstants.USER)).getId();

		String message = Validator.validateContacts(phone, email);

		if (message == null) {

			UserService userService = ServiceFactory.getInstance().getUserService();

			try {
				userService.setContacts(userId, phone, email);
				message = "contacts_updated";
				// other exception with wrong password
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		}
		request.setAttribute(RequestConstants.SET_CONTACTS_MESSAGE, message);
		return new ControllerSendRedirect(PageNames.PERSONAL_DETAILS);
	}
}