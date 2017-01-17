package by.newnet.command.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import by.newnet.service.exception.UserAlreadyExistingException;

public class Registration implements Command {

	private static final String PASSWORD = "password";
	private static final String REENTER_PASSWORD = "reenterPassword";
	private static final String PHONE = "phone";
	private static final String EMAIL = "email";
	private static final String REGISTRATION_MESSAGE = "registrationMessage";
	public static final Pattern EMAIL_PATTERN = Pattern.compile("[A-z0-9]+@[A-z0-9]+]\\.[A-z]");
	public static final Pattern PHONE_PATTERN = Pattern.compile("\\d{9}");

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String password;
		String reenterPassword;
		String phone;
		String email;

		password = request.getParameter(PASSWORD);
		reenterPassword = request.getParameter(REENTER_PASSWORD);
		phone = request.getParameter(PHONE);
		email = request.getParameter(EMAIL);

		String message = validation(password, reenterPassword, phone, email);

		if (message == null) {
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				userService.register(password, reenterPassword, phone, email);
			} catch (ServiceException e) {
				// exception?message needed? message registration failed
				message = "";
				throw new CommandException(e);
			}
		}
		request.setAttribute(REGISTRATION_MESSAGE, message);
		return PageNames.INDEX;

	}

	private String validation(String password, String reenterPassword, String phone, String email) {
		if (StringUtils.isEmpty(password) || StringUtils.isEmpty(reenterPassword)
		        || StringUtils.isEmpty(phone) || StringUtils.isEmpty(email)) {
			return "empty_fields";
		}
		if (!password.equals(reenterPassword)) {
			return "different_passwords";
		}
		Matcher phoneMatcher = PHONE_PATTERN.matcher(phone);
		if (!phoneMatcher.matches()) {
			return "incorrect_phone";
		}
		Matcher emailMatcher = EMAIL_PATTERN.matcher(email);
		if (!emailMatcher.matches()) {
			return "incorrect_email";
		}
		return null;
	}
	
/*	// private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String REPEAT_PASSWORD = "repeatPassword";
	private static final String NAME = "name";
	private static final String REGISTRATION_MESSAGE = "registrationMessage";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {
// login or account?
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
			user.setAccount(login);
			user.setPassword(password);
			user.setFirstName(name);

			UserService userService = ServiceFactory.getInstance().getUserService();

			try {
				userService.register(user);
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
	}*/
}
