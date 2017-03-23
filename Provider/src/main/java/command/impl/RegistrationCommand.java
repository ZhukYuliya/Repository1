package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import command.constant.PageNames;
import command.constant.RequestConstants;
import command.exception.CommandException;
import command.validator.Validator;
import controller.ControllerAction;
import controller.ControllerForward;
import controller.ControllerSendRedirect;
import model.User;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

/**
 * The Class RegistrationCommand. Saves the user's password and contacts he
 * provides on creating his account.
 */
public class RegistrationCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {
		ControllerAction controllerAction = null;

		int userId = Integer.valueOf((request.getParameter(RequestConstants.USER_ID)));
		String password = request.getParameter(RequestConstants.PASSWORD);
		String reenterPassword = request.getParameter(RequestConstants.REENTER_PASSWORD);
		String phone = request.getParameter(RequestConstants.PHONE);
		String email = request.getParameter(RequestConstants.EMAIL);

		String message = Validator.validateRegistration(password, reenterPassword, phone, email);
		String page = null;

		if (message == null) {
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				/*
				 * Redirects the user to index page saying that his registration succeded and he can
				 * access his account with the password he has just created.
				 */
				userService.register(userId, password, phone, email);
				message = RequestConstants.SUCCESSFUL_REGISTRATION;
				page = PageNames.INDEX;
				controllerAction = new ControllerSendRedirect(
				        page + "?" + RequestConstants.REGISTRATION_MESSAGE + "=" + message);
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		} else {
			/*
			 * Leaves the user at registration page showing message about what
			 * is wrong with the data he is trying to submit. Saves the user details
			 * for printing on jsp page when forwarding request.
			 */
			User user = new User();
			user.setId(userId);
			user.setSecondName(request.getParameter(RequestConstants.SECOND_NAME));
			user.setFirstName(request.getParameter(RequestConstants.FIRST_NAME));
			request.setAttribute(RequestConstants.USER, user);
			request.setAttribute(RequestConstants.REGISTRATION_MESSAGE, message);
			page = PageNames.REGISTRATION;
			controllerAction = new ControllerForward(page);
		}
		return controllerAction;
	}
}
