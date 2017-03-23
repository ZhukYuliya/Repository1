package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import command.constant.PageNames;
import command.constant.RequestConstants;
import command.exception.CommandException;
import command.validator.Validator;
import controller.ControllerAction;
import controller.ControllerSendRedirect;
import model.Request;
import service.RequestService;
import service.ServiceFactory;
import service.exception.ServiceException;

/**
 * The Class PostRequestCommand. Saves a guest's request so that it's visible to the operator
 * that should proceed this request.
 */
public class PostRequestCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String name = request.getParameter(RequestConstants.NAME);
		String email = request.getParameter(RequestConstants.EMAIL);
		String phone = request.getParameter(RequestConstants.PHONE);
		String address = request.getParameter(RequestConstants.ADDRESS);

		String message = Validator.validateRequest(name, email, phone, address);

		if (message == null) {
			Request clientRequest = new Request();
			clientRequest.setFirstName(name);
			clientRequest.setEmail(email);
			clientRequest.setPhone(phone);
			clientRequest.setAddress(address);

			RequestService requestService = ServiceFactory.getInstance().getRequestService();
			try {
				requestService.postRequest(clientRequest);
				message = RequestConstants.REQUEST_POSTED;
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		}
		return new ControllerSendRedirect(PageNames.TO_INDEX_COMMAND+ "&"
		        + RequestConstants.POST_REQUEST_MESSAGE + "=" + message);
	}
}
