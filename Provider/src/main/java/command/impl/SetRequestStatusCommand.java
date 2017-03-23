package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import command.CommandName;
import command.constant.PageNames;
import command.constant.RequestConstants;
import command.exception.CommandException;
import controller.ControllerAction;
import controller.ControllerSendRedirect;
import model.RequestStatus;
import service.RequestService;
import service.ServiceFactory;
import service.exception.ServiceException;

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
		switch (statusParameter){
		case RequestConstants.AFTER_CALL:
			message = RequestConstants.REQUEST_PROCESSED;
			/*
			 * Redirects the operator to the page with requests list saying that the status was
			 * updated.
			 */
			controllerAction = new ControllerSendRedirect(CommandName.SHOW_REQUESTS 
					+ "&" + RequestConstants.CHANGE_STATUS_MESSAGE + "=" + message);
			break;
		case RequestConstants.AFTER_CONTRACT:
			/*
			 * Redirects the operator to the page with a form for filling details of a newly 
			 * signed contract.
			 */
			controllerAction = new ControllerSendRedirect(PageNames.REGISTER_NEW_CONTRACT_COMMAND);
			break;
		}
		return controllerAction;
	}

}
