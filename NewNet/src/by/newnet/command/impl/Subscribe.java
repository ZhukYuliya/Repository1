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
	private static final String NEW_TARIFF = "newTariff";
	private static final String SUBSCRIPTION_MESSAGE = "subscriptionMessage";
	private static final String USER = "user";
	public static final String ADMIN = "admin";
	public static final String CUSTOMER = "customer";
	public static final String OPERATOR = "operator";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		int newTariffId;
		newTariffId = Integer.valueOf(request.getParameter(NEW_TARIFF));
		// get session(true) not needed?
		// user or int parameter?
		User user = (User) request.getSession().getAttribute(USER);
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
		request.setAttribute(SUBSCRIPTION_MESSAGE, message);
		String page = null;
		String role = user.getRole().getName();
		switch (role) {
		// is show account command needed?
		case CUSTOMER:
			page = PageNames.SHOW_ACCOUNT_COMMAND;
		case OPERATOR:
			page = PageNames.OPERATOR;
		case ADMIN:
			page = PageNames.ADMIN;
		// return to the page from which this request was sent
		// return to tariffs or home

		}
		return page;

	}
}
