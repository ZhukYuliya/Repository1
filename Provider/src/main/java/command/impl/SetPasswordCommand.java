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
import service.exception.ServiceAuthenticationException;
import service.exception.ServiceException;

/**
 * The Class SetPasswordCommand. Updates the password of a user
 */
public class SetPasswordCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String oldPassword = request.getParameter(RequestConstants.OLD_PASSWORD);
		String newPassword = request.getParameter(RequestConstants.NEW_PASSWORD);
		String reenterNewPassword = request.getParameter(RequestConstants.REENTER_NEW_PASSWORD);
		int userId = ((User) request.getSession().getAttribute(RequestConstants.USER)).getId();

		String message = Validator.validatePasswordUpdate(oldPassword, newPassword, reenterNewPassword);

		if (message == null) {
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				userService.setPassword(userId, oldPassword, newPassword);
				message = RequestConstants.PASSWORD_CHANGED;
			} catch (ServiceAuthenticationException e) {
				message = RequestConstants.WRONG_PASSWORD;
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		}
		/*
		 * Redirects the user to the same page saying either that the password was updated
		 * or that the password is wrong.
		 */
		return new ControllerSendRedirect(PageNames.TO_PERSONAL_DETAILS_COMMAND 
				+ "&" + RequestConstants.SET_PASSWORD_MESSAGE + "=" + message);
	}
}