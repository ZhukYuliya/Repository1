package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.Request;
import by.newnet.service.RequestService;
import by.newnet.service.ServiceFactory;
import by.newnet.service.exception.ServiceException;
import by.newnet.service.exception.UserAlreadyExistingException;

public class PostRequest implements Command {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String firstName;
		String email;
		String phone;
		String address;

		firstName = request.getParameter(RequestConstants.FIRST_NAME);
		email = request.getParameter(RequestConstants.EMAIL);
		phone = request.getParameter(RequestConstants.PHONE);
		address = request.getParameter(RequestConstants.ADDRESS);
		// what validation needed?
		String message = Validator.validateRequest(firstName, email, phone, address);

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
				// some other excepion
			} catch (UserAlreadyExistingException e) {
				message = "user_already_existing";
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		}
		request.setAttribute(RequestConstants.POST_REQUEST_MESSAGE, message);
		return PageNames.SHOW_REQUESTS_COMMAND;
	}
}
