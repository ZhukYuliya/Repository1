package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.constant.PageNames;
import by.newnet.command.constant.RequestConstants;
import by.newnet.command.exception.CommandException;
import by.newnet.command.validator.Validator;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerForward;
import by.newnet.controller.ControllerSendRedirect;
import by.newnet.model.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

/**
 * The Class RegistrationCommand. Saves the user's password and contacts he
 * provides on creating his account.
 */
public class RegistrationCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {
		ControllerAction controllerAction = null;

		String password;
		String reenterPassword;
		String phone;
		String email;

		int userId = Integer.valueOf((request.getParameter(RequestConstants.USER_ID)));
		password = request.getParameter(RequestConstants.PASSWORD);
		reenterPassword = request.getParameter(RequestConstants.REENTER_PASSWORD);
		phone = request.getParameter(RequestConstants.PHONE);
		email = request.getParameter(RequestConstants.EMAIL);

		String message = Validator.validateRegistration(password, reenterPassword, phone, email);
		String page = null;

		if (message == null) {
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				/**
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
			/**
			 * Leaves the user at registration page showing message about what
			 * is wrong with the data he is trying to submit. Saves the user 
			 * for not to lose his data when forwarding.
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
