package by.newnet.command.impl;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerForward;
import by.newnet.domain.Request;
import by.newnet.service.RequestService;
import by.newnet.service.ServiceFactory;
import by.newnet.service.exception.ServiceException;

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
		Collections.sort(requestsList);
		request.setAttribute(RequestConstants.REQUESTS_LIST, requestsList);
		return new ControllerForward(PageNames.REQUESTS);
	}

}
