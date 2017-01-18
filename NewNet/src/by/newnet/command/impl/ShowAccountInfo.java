package by.newnet.command.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.User;
import by.newnet.service.TariffService;
import by.newnet.service.UserService;
import by.newnet.service.ServiceFactory;
import by.newnet.service.exception.ServiceException;

public class ShowAccountInfo implements Command {

	public static final String ACCOUNT_INFO = "accountInfo";
	public static final String USER = "user";
	public static final String ADMIN = "admin";
	public static final String CUSTOMER = "customer";
	public static final String OPERATOR = "operator";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		UserService userService = ServiceFactory.getInstance().getUserService();
		User user = (User) request.getSession().getAttribute(USER);
		int userId = user.getId();
		try {
			user = userService.getUserById(userId);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
//to request or session?
		request.setAttribute(USER, user);
		// what to do for operator , admin?
		String role = user.getRole().getName();
		String page = null;
		switch (role) {
		// is show account command needed?
		case CUSTOMER:
			page = PageNames.HOME;
			break;
		case OPERATOR:
			page = PageNames.OPERATOR;
			break;
		case ADMIN:
			page = PageNames.ADMIN;
			break;
		}
		return page;
	}
}
