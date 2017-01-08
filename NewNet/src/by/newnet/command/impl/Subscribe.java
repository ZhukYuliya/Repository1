package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class Subscribe implements Command {
	private static final String NEW_TARIFF = "newTariff";
	private static final String SUBSCRIBE_FOR_TARIFF_MESSAGE = "subscriptionMessage";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		int newTariffId;
		newTariffId = Integer.valueOf(request.getParameter(NEW_TARIFF));

		UserService userService = ServiceFactory.getInstance().getUserService();
		// hard code message?
		String message = null;
		try {
			if (userService.subscribeTariff(newTariffId)) {
				message = "successfull_subscription";
			} else {
				message = "unsuccessfull_subscription";
			}
			// some other excepion
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		request.setAttribute(SUBSCRIBE_FOR_TARIFF_MESSAGE, message);
		// return to the page from which this request was sent
		return PageNames.HOME;

	}

}
