package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;
import by.newnet.service.exception.UserAlreadyExistingException;

public class SetPassword implements Command {
	private static final String OLD_PASSWORD = "oldPassword";
	private static final String NEW_PASSWORD = "newPassword";
	private static final String REENTER_NEW_PASSWORD = "reenterNewPassword";
	private static final String REGISTRATION_MESSAGE = "registrationMessage";
	private static final String USER = "user";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String oldPassword;
		String newPassword;
		String reenterNewPassword;

		oldPassword = request.getParameter(OLD_PASSWORD);
		newPassword = request.getParameter(NEW_PASSWORD);
		reenterNewPassword = request.getParameter(REENTER_NEW_PASSWORD);
		
		int userId = ((User)request.getSession().getAttribute(USER)).getId();

		String message = validation(oldPassword, newPassword, reenterNewPassword);
		
		if (message == null) {

			UserService userService = ServiceFactory.getInstance().getUserService();

			try {
				userService.setPassword(userId, oldPassword, newPassword);
				message = "password_changed";
				//other exception with wrong password
			} catch (UserAlreadyExistingException e) {
				message = "user_already_existing";
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		}
		request.setAttribute(REGISTRATION_MESSAGE, message);
		return PageNames.PERSONAL_DETAILS;

	}

	private String validation(String oldPassword, String newPassword, String reenterNewPassword) {
		if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)
		        || StringUtils.isEmpty(reenterNewPassword)) {
			return "empty_fields";
		}
		if (!newPassword.equals(reenterNewPassword)) {
			return "different_passwords";
		}

		return null;
	}
}