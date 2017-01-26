package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerForward;
import by.newnet.controller.ControllerSendRedirect;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceAuthorizationException;
import by.newnet.service.exception.ServiceException;

public class AuthenticationCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {
		ControllerAction controllerAction = null;
		String account;
		String password;

		account = request.getParameter(RequestConstants.ACCOUNT);
		password = request.getParameter(RequestConstants.PASSWORD);

		String message = Validator.checkEmptyFields(account, password);
		String page = PageNames.INDEX;

		if (message == null) {
			UserService userService = ServiceFactory.getInstance().getUserService();
			User loggedUser = null;
			try {
				loggedUser = userService.authenticate(account, password);
			} catch (ServiceAuthorizationException e) {
				message = "wrong_credentials";
				request.setAttribute(RequestConstants.AUTHENTICATION_FAILED, true);
				page = PageNames.INDEX;
				controllerAction = new ControllerSendRedirect(page);
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
			if (loggedUser != null) {
				HttpSession session = request.getSession();
				session.setAttribute(RequestConstants.USER, loggedUser);
				page = PageNames.SHOW_ACCOUNT_COMMAND;
				controllerAction = new ControllerForward(page);
			} else{
				message = "wrong_credentials";
				request.setAttribute(RequestConstants.AUTHENTICATION_FAILED, true);
				page = PageNames.INDEX;
			}
		}
		request.setAttribute(RequestConstants.AUTHENTICATION_MESSAGE,message);
		return controllerAction;
	}
}
