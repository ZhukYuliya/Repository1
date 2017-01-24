package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class Subscribe implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		int newTariffId;
		newTariffId = Integer.valueOf(request.getParameter(RequestConstants.NEW_TARIFF));
		// get session(true) not needed?
		// user or int parameter?
		User user = (User) request.getSession().getAttribute(RequestConstants.USER);
		int userId = user.getId();
		UserService userService = ServiceFactory.getInstance().getUserService();
		// hard code message?
		String message = null;
		try {
			userService.subscribeTariff(userId, newTariffId);
			message = "successfull_subscription";
			// some other excepion
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		request.setAttribute(RequestConstants.SUBSCRIPTION_MESSAGE, message);
		return PageNames.SHOW_ACCOUNT_COMMAND;
	}
}
