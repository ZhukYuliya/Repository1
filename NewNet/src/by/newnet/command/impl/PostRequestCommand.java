package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.constant.PageNames;
import by.newnet.command.constant.RequestConstants;
import by.newnet.command.exception.CommandException;
import by.newnet.command.validator.Validator;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerSendRedirect;
import by.newnet.model.Request;
import by.newnet.service.RequestService;
import by.newnet.service.ServiceFactory;
import by.newnet.service.exception.ServiceException;

public class PostRequestCommand implements Command {
	
	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String name = request.getParameter(RequestConstants.NAME);
		String email = request.getParameter(RequestConstants.EMAIL);
		String phone = request.getParameter(RequestConstants.PHONE);
		String address = request.getParameter(RequestConstants.ADDRESS);
		// what validation needed?
		String message = Validator.validateRequest(name, email, phone, address);

		if (message == null) {
			Request clientRequest = new Request();
			clientRequest.setFirstName(name);
			clientRequest.setEmail(email);
			clientRequest.setPhone(phone);
			clientRequest.setAddress(address);

			RequestService requestService = ServiceFactory.getInstance().getRequestService();
			// hard code message?
			try {
				requestService.postRequest(clientRequest);
				message = "request_posted";
				// some other excepion
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		}
		request.setAttribute(RequestConstants.POST_REQUEST_MESSAGE, message);
		return new ControllerSendRedirect(PageNames.AFTER_POST_REQUEST);
	}
}
