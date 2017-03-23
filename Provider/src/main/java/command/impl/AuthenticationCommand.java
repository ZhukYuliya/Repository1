package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import service.exception.ServiceAuthenticationException;
import service.exception.ServiceException;

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
				/*
				 * Puts the user in session and redirects him to his home page with his 
				 * account info in case authentication succeded.
				 */
				session.setAttribute(RequestConstants.USER, loggedUser);
				controllerAction = new ControllerSendRedirect(PageNames.SHOW_ACCOUNT_COMMAND);
			} catch (ServiceAuthenticationException e) {
				/*
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
