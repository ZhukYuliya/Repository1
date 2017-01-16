package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class Authentication implements Command {

	private static final String ACCOUNT = "account";
	private static final String PASSWORD = "password";
	private static final String AUTHENTICATION_FAILED = "authenticationFailed";
	private static final String AUTHENTICATION_MESSAGE = "authenticationMessage";
	public static final String USER = "user";
	public static final String ADMIN = "admin";
	public static final String CUSTOMER = "customer";
	public static final String OPERATOR = "operator";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String account;
		String password;

		account = request.getParameter(ACCOUNT);
		password = request.getParameter(PASSWORD);

		String message = validation(account, password);
		String page = null;

		if (message == null) {

			UserService userService = ServiceFactory.getInstance().getUserService();

			User loggedUser = null;
			try {
				loggedUser = userService.authenticate(account, password);
			} catch (ServiceException e) {
				throw new CommandException(e);
			}

			if (loggedUser != null) {
				HttpSession session = request.getSession();
				session.setAttribute(USER, loggedUser);
				page = PageNames.SHOW_ACCOUNT_COMMAND;
			}
		} else {
			request.setAttribute(AUTHENTICATION_FAILED, true);
			request.setAttribute(AUTHENTICATION_MESSAGE,message);
			page = PageNames.INDEX;

		}
		return page;

	}

	private String validation(String account, String password) {
		if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
			return "empty_fields";
		}
		return null;
	}
}
