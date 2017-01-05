package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;
import by.newnet.service.exception.UserAlreadyExistingException;

public class Registration implements Command {
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String REPEAT_PASSWORD = "repeatPassword";
	private static final String NAME = "name";
	private static final String REGISTRATION_MESSAGE = "registrationMessage";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String login;
		String password;
		String repeatPassword;
		String name;

		login = request.getParameter(LOGIN);
		password = request.getParameter(PASSWORD);
		repeatPassword = request.getParameter(REPEAT_PASSWORD);
		name = request.getParameter(NAME);

		String message = validation(login, password, repeatPassword, name);
		
		if (message == null) {
			User user = new User();
			user.setLogin(login);
			user.setPassword(password);
			user.setName(name);

			UserService userService = ServiceFactory.getInstance().getUserService();

			try {
				userService.registration(user);
				message = "user_created";
			} catch (UserAlreadyExistingException e) {
				message = "user_already_existing";
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		}
		request.setAttribute(REGISTRATION_MESSAGE, message);
		return PageNames.INDEX;

	}

	private String validation(String login, String password, String repeatPassword, String name) {
		if (StringUtils.isEmpty(login) || StringUtils.isEmpty(password) || StringUtils.isEmpty(name)
		        || StringUtils.isEmpty(repeatPassword)) {
			return "empty_fields";
		}
		if (!password.equals(repeatPassword)) {
			return "different_passwords";
		}

		return null;
	}
}