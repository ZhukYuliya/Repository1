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
import model.Tariff;
import model.User;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

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
				/*
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
			/*
			 * If the user's fields validation failed, leaves the admin at the same page saying
			 * what is wrong with the input.
			 */
			request.setAttribute(RequestConstants.USER_EDITING_MESSAGE, message);
			controllerAction = new ControllerForward(PageNames.EDIT_USER);
		}
		return controllerAction;
	}
}