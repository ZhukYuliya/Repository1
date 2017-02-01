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
import by.newnet.model.Tariff;
import by.newnet.model.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

/**
 * The Class SaveUserCommand. Saves the updates an admin made to a customer's or an operator's profile.
 */
public class SaveUserCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		int id = Integer.valueOf(request.getParameter(RequestConstants.ID));
		String account = request.getParameter(RequestConstants.ACCOUNT);
		String firstName = request.getParameter(RequestConstants.FIRST_NAME);
		String secondName = request.getParameter(RequestConstants.SECOND_NAME);
		String phone = request.getParameter(RequestConstants.PHONE);
		String email = request.getParameter(RequestConstants.EMAIL);

		String message = Validator.validateSaveUser(account, firstName, secondName, phone, email);
		ControllerAction controllerAction = null;

		if (message == null) {
			User user = new User();
			user.setId(id);
			user.setAccount(account);
			user.setFirstName(firstName);
			user.setSecondName(secondName);
			user.setPhone(phone);
			user.setEmail(email);
			Tariff tariff = new Tariff();
			tariff.setId(Integer.valueOf(request.getParameter(RequestConstants.TARIFF)));
			user.setTariff(tariff);
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				/**
				 * Redirects the admin to the page with users list and notifies him that user
				 * was updated.
				 */
				userService.saveUser(user);
				message = RequestConstants.SUCCESSFUL_USER_EDITING;
				controllerAction = new ControllerSendRedirect(PageNames.SHOW_USERS_COMMAND + "&"
				        + RequestConstants.USER_EDITING_MESSAGE + "=" + message);
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		} else {
			/**
			 * If the user's fields validation failed, leaves the admin at the same page saying
			 * what is wrong with the input.
			 */
			request.setAttribute(RequestConstants.USER_EDITING_MESSAGE, message);
			controllerAction = new ControllerForward(PageNames.EDIT_USER);
		}
		return controllerAction;
	}
}