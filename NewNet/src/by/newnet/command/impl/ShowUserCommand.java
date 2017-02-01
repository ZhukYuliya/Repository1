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
import by.newnet.model.Tariff;
import by.newnet.model.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.TariffService;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

/**
 * The Class ShowUserCommand. Forwards admin to the page with a form for user's info editing.
 */
public class ShowUserCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		UserService userService = ServiceFactory.getInstance().getUserService();
		TariffService tariffService = ServiceFactory.getInstance().getTariffService();

		int userId = (Integer.valueOf(request.getParameter(RequestConstants.ID)));
		User user = null;
		List<Tariff> tariffsList = null;
		try {
			user = userService.getUserById(userId);
			/*
			 * Extracts the tariffs list too so that it is possible to choose and set a tariff 
			 * for a user from this list.
			 */
			tariffsList = tariffService.showTariffs();
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		request.setAttribute(RequestConstants.USER, user);
		request.setAttribute(RequestConstants.TARIFFS_LIST, tariffsList);
		return new ControllerForward(PageNames.EDIT_USER);
	}

}
