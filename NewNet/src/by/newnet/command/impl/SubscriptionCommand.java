package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerSendRedirect;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class SubscriptionCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		int newTariffId;
		newTariffId = Integer.valueOf(request.getParameter(RequestConstants.NEW_TARIFF));
		User user = (User) request.getSession().getAttribute(RequestConstants.USER);
		int userId = user.getId();
		UserService userService = ServiceFactory.getInstance().getUserService();
		String message = null;
		try {
			userService.subscribeTariff(userId, newTariffId);
			message = "successfull_subscription";
			// some other excepion
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return new ControllerSendRedirect(PageNames.SHOW_ACCOUNT_COMMAND + "?" 
		+ RequestConstants.SUBSCRIPTION_MESSAGE + "?" + message);
	}
}
