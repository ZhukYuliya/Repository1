package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.constant.PageNames;
import by.newnet.command.constant.RequestConstants;
import by.newnet.command.exception.CommandException;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerForward;
import by.newnet.controller.ControllerSendRedirect;
import by.newnet.model.RequestStatus;
import by.newnet.service.RequestService;
import by.newnet.service.ServiceFactory;
import by.newnet.service.exception.ServiceException;

/**
 * The Class SetRequestStatusCommand. Updates a request's status when it get's processed or a contract
 * is signed for a certain request.
 */
public class SetRequestStatusCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		ControllerAction controllerAction = null;
		RequestService requestService = ServiceFactory.getInstance().getRequestService();
		
		int requestId = Integer.valueOf(request.getParameter(RequestConstants.ID));
		String statusParameter = (request.getParameter(RequestConstants.STATUS));
		RequestStatus status = RequestStatus.valueOf(statusParameter);
		try {
			requestService.setStatus(requestId, status);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		String message = null;
		String page = null;
		switch (statusParameter){
		case RequestConstants.AFTER_CALL:
			page = PageNames.SHOW_REQUESTS_COMMAND;
			message = RequestConstants.REQUEST_PROCESSED;
			/**
			 * Redirects the operator to the page with requests list saying that the status was
			 * updated.
			 */
			request.setAttribute(RequestConstants.CHANGE_STATUS_MESSAGE, message);
			controllerAction = new ControllerForward(page);
			break;
		case RequestConstants.AFTER_CONTRACT:
			/**
			 * Redirects the operator to the page with a form for filling details of a newly 
			 * signed contract.
			 */
			page = PageNames.REGISTER_NEW_CONTRACT_COMMAND;
			controllerAction = new ControllerSendRedirect(page);
			break;
		}
		return controllerAction;
	}

}
