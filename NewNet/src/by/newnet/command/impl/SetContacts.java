package by.newnet.command.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceAuthorizationException;
import by.newnet.service.exception.ServiceException;
import by.newnet.service.exception.UserAlreadyExistingException;

public class SetContacts implements Command {
	private static final String PHONE = "phone";
	private static final String EMAIL = "email";
	private static final String USER = "user";
	private static final String REGISTRATION_MESSAGE = "registrationMessage";
	public static final Pattern EMAIL_PATTERN =
	        Pattern.compile("[A-z0-9]+@[A-z0-9]+]\\.[A-z]");
	public static final Pattern PHONE_PATTERN =
	        Pattern.compile("\\d{7}");

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String phone;
		String email;

		phone = request.getParameter(PHONE);
		email = request.getParameter(EMAIL);

		int userId = ((User) request.getSession().getAttribute(USER)).getId();

		String message = validation(phone, email);

		if (message == null) {

			UserService userService = ServiceFactory.getInstance().getUserService();

			try {
				userService.setPassword(userId, phone, email);
				message = "password_changed";
				// other exception with wrong password
			} catch (ServiceAuthorizationException e) {
				message = "user_already_existing";
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		}
		request.setAttribute(REGISTRATION_MESSAGE, message);
		return PageNames.PERSONAL_DETAILS;

	}

	private String validation(String phone, String email) {
		if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(email)) {
			return "empty_fields";
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
}