package by.newnet.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.constant.PageNames;
import by.newnet.command.constant.RequestConstants;
import by.newnet.command.exception.CommandException;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerForward;
import by.newnet.model.Request;
import by.newnet.service.RequestService;
import by.newnet.service.ServiceFactory;
import by.newnet.service.exception.ServiceException;

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
