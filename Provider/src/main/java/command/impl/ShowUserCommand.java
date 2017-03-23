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
import model.Tariff;
import model.User;
import service.ServiceFactory;
import service.TariffService;
import service.UserService;
import service.exception.ServiceException;

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
