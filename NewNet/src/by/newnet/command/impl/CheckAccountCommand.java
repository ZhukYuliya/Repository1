package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerForward;
import by.newnet.controller.ControllerSendRedirect;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;
import by.newnet.service.exception.UserAlreadyExistingException;

public class CheckAccountCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String account;
		account = request.getParameter(RequestConstants.ACCOUNT);
		String message = Validator.checkEmptyFields(account);
		if (message == null) {
			message = Validator.validateContract(account);
		}
		String page = null;
		User user = null;
		ControllerAction controllerAction = null;
		// logic in command?
		if (message == null) {
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				user = userService.getUserForRegistration(account);
			} catch (UserAlreadyExistingException e) {
				message = "account_exists";
				request.setAttribute(RequestConstants.CHECK_ACCOUNT_MESSAGE, message);
				page = PageNames.INDEX;
				controllerAction = new ControllerForward(page);
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
			if (user != null) {
				int userId = user.getId();
				page = PageNames.TO_REGISTRATION_COMMAND;
				controllerAction = new ControllerSendRedirect(
				        page + "&" + RequestConstants.USER_ID + "=" + userId);
			}
		} else {
			request.setAttribute(RequestConstants.CHECK_ACCOUNT_MESSAGE, message);
			page = PageNames.INDEX;
			controllerAction = new ControllerForward(page);
		}
		return controllerAction;
	}
}
