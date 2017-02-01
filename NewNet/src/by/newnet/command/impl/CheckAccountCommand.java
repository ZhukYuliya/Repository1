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
import by.newnet.service.exception.ServiceAuthenticationException;
import by.newnet.service.exception.ServiceException;
import by.newnet.service.exception.UserAlreadyExistingServiceException;

/**
 * The Class CheckAccountCommand. For a guest who signed a contract with Newnet and wants to register 
 * and create his account using the contract number, checks if a user with such contract number already
 * has an account.
 */
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
		String page;
		User user = null;
		ControllerAction controllerAction = null;
		if (message == null) {
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				user = userService.getUserForRegistration(account);
				/**
				 * Leaves the user at index page showing message about already existing account
				 * in case such contract number has already been used for registration or in case
				 * such contract number wasn't found in DB.
				 */
			} catch (ServiceAuthenticationException e) {
				message = RequestConstants.NON_EXISTING_CONTRACT_NUMBER;
				request.setAttribute(RequestConstants.CHECK_ACCOUNT_MESSAGE, message);
				page = PageNames.INDEX;
				controllerAction = new ControllerForward(page);
			} catch (UserAlreadyExistingServiceException e) {
				message = RequestConstants.ACCOUNT_EXISTS;
				request.setAttribute(RequestConstants.CHECK_ACCOUNT_MESSAGE, message);
				page = PageNames.INDEX;
				controllerAction = new ControllerForward(page);
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
			if (user != null) {
				/**
				 * Redirects the user to the registration page in case such account number 
				 * has been found in DB, but it hasn't been used for registration yet.
				 */
				int userId = user.getId();
				page = PageNames.TO_REGISTRATION_COMMAND;
				controllerAction = new ControllerSendRedirect(
				        page + "&" + RequestConstants.USER_ID + "=" + userId);
			}
		} else {
			/**
			 * Leaves the user at index page showing message about invalid contract number.
			 */
			request.setAttribute(RequestConstants.CHECK_ACCOUNT_MESSAGE, message);
			page = PageNames.INDEX;
			controllerAction = new ControllerForward(page);
		}
		return controllerAction;
	}
}
