package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerSendRedirect;
import by.newnet.model.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceAuthorizationException;
import by.newnet.service.exception.ServiceException;

public class SetPasswordCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String oldPassword;
		String newPassword;
		String reenterNewPassword;

		oldPassword = request.getParameter(RequestConstants.OLD_PASSWORD);
		newPassword = request.getParameter(RequestConstants.NEW_PASSWORD);
		reenterNewPassword = request.getParameter(RequestConstants.REENTER_NEW_PASSWORD);

		int userId = ((User) request.getSession().getAttribute(RequestConstants.USER)).getId();

		String message = Validator.validatePasswordUpdate(oldPassword, newPassword, reenterNewPassword);

		if (message == null) {
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				userService.setPassword(userId, oldPassword, newPassword);
				message = "password_changed";
				// other exception with wrong password
			} catch (ServiceAuthorizationException e) {
				message = "wrong_password";
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		}
		return new ControllerSendRedirect(PageNames.CHANGE_PERSONAL_DETAILS_COMMAND 
				+ "&" + RequestConstants.SET_PASSWORD_MESSAGE + "=" + message);
	}
}