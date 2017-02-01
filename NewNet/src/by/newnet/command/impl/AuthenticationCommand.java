package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import by.newnet.service.exception.ServiceAuthorizationException;
import by.newnet.service.exception.ServiceException;

/**
 * The Class AuthenticationCommand. Authenticates user with the account number serving as login and 
 * the password the user provides.
 */
public class AuthenticationCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {
		ControllerAction controllerAction = null;
		String account = request.getParameter(RequestConstants.ACCOUNT);
		String password = request.getParameter(RequestConstants.PASSWORD);

		String message = Validator.checkEmptyFields(account, password);

		if (message == null) {
			UserService userService = ServiceFactory.getInstance().getUserService();
			User loggedUser;
			try {
				loggedUser = userService.authenticate(account, password);
				HttpSession session = request.getSession();
				/**
				 * Puts the user in session and redirects him to his home page with his 
				 * account info in case authentication succeded.
				 */
				session.setAttribute(RequestConstants.USER, loggedUser);
				controllerAction = new ControllerSendRedirect(PageNames.SHOW_ACCOUNT_COMMAND);
			} catch (ServiceAuthorizationException e) {
				/**
				 * Leaves the user at index page showing message about wrong credentials 
				 * in case authentication did not succeded.
				 */
				message = RequestConstants.WRONG_CREDENTIALS;
				request.setAttribute(RequestConstants.AUTHENTICATION_MESSAGE, message);
				controllerAction = new ControllerForward(PageNames.INDEX);
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		}
		return controllerAction;
	}
}
