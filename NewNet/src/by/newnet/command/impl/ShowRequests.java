package by.newnet.command.impl;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.Request;
import by.newnet.service.RequestService;
import by.newnet.service.ServiceFactory;
import by.newnet.service.exception.ServiceException;

public class ShowRequests implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		RequestService requestService = ServiceFactory.getInstance().getRequestService();
		List<Request> requestsList = null;
		try {
			requestsList = requestService.showRequests();
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		Collections.sort(requestsList);
		request.setAttribute(RequestConstants.REQUESTS_LIST, requestsList);
		return PageNames.REQUESTS;
	}

}
