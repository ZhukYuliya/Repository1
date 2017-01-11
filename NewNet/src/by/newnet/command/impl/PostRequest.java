package by.newnet.command.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.Request;
import by.newnet.domain.User;
import by.newnet.service.TariffService;
import by.newnet.service.UserService;
import by.newnet.service.RequestService;
import by.newnet.service.ServiceFactory;
import by.newnet.service.exception.ServiceException;
import by.newnet.service.exception.UserAlreadyExistingException;

public class PostRequest implements Command {
	private static final String FIRST_NAME = "firstName";
	private static final String EMAIL = "email";
	private static final String PHONE = "phone";
	private static final String ADDRESS = "address";
	private static final String POST_REQUEST_MESSAGE = "registrationMessage";
	public static final Pattern EMAIL_PATTERN =
	        Pattern.compile("[A-z0-9]+@[A-z0-9]+]\\.[A-z]");
	public static final Pattern PHONE_PATTERN =
	        Pattern.compile("\\d{9}");

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String firstName;
		String email;
		String phone;
		String address;

		firstName = request.getParameter(FIRST_NAME);
		email = request.getParameter(EMAIL);
		phone = request.getParameter(PHONE);
		address = request.getParameter(ADDRESS);
		// what validation needed?
		String message = validation(firstName, email, phone, address);

		if (message == null) {
			Request clientRequest = new Request();
			clientRequest.setFirstName(firstName);
			clientRequest.setEmail(email);
			clientRequest.setPhone(phone);
			clientRequest.setAddress(address);

			RequestService requestService = ServiceFactory.getInstance().getRequestService();
			// hard code message?
			try {
				requestService.postRequest(clientRequest);
				message = "request_posted";
				// soe other excepion
			} catch (UserAlreadyExistingException e) {
				message = "user_already_existing";
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		}
		request.setAttribute(POST_REQUEST_MESSAGE, message);
		return PageNames.INDEX;

	}

	// todo!
	private String validation(String firstName, String email, String phone, String address) {
		if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(email) || StringUtils.isEmpty(phone)
		        || StringUtils.isEmpty(address)) {
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
