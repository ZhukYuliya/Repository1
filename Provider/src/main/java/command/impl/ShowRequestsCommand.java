package command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import command.constant.PageNames;
import command.constant.RequestConstants;
import command.exception.CommandException;
import controller.ControllerAction;
import controller.ControllerForward;
import model.Request;
import service.RequestService;
import service.ServiceFactory;
import service.exception.ServiceException;

/**
 * The Class ShowRequestsCommand. Shows the list of all the requests with different statuses to operator.
 */
public class ShowRequestsCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		RequestService requestService = ServiceFactory.getInstance().getRequestService();
		List<Request> requestsList = null;
		try {
			requestsList = requestService.showRequests();
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		request.setAttribute(RequestConstants.REQUESTS_LIST, requestsList);
		return new ControllerForward(PageNames.REQUESTS);
	}

}
