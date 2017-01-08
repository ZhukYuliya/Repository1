package by.newnet.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.User;
import by.newnet.service.TariffService;
import by.newnet.service.UserService;
import by.newnet.service.ServiceFactory;
import by.newnet.service.exception.ServiceException;
import by.newnet.service.exception.UserAlreadyExistingException;

public class PostRequest implements Command {
	private static final String FIRST_NAME = "firstName";
	private static final String EMAIL = "email";
	private static final String PHONE = "phone";
	private static final String ADDRESS = "address";
	private static final String POST_REQUEST_MESSAGE = "registrationMessage";

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
			User preCustomer = new User();
			preCustomer.setFirstName(firstName);
			preCustomer.setEmail(email);
			preCustomer.setPhone(phone);
			preCustomer.setAddress(address);

			UserService userService = ServiceFactory.getInstance().getUserService();
// hard code message?
			try {
				userService.postRequest(preCustomer);
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
