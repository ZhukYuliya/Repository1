package by.newnet.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.Tariff;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.TariffService;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class ShowUser implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		UserService userService = ServiceFactory.getInstance().getUserService();
		TariffService tariffService = ServiceFactory.getInstance().getTariffService();

		int userId = ((User)request.getSession().getAttribute(RequestConstants.USER)).getId();
		User user = null;
		List<Tariff> tariffsList = null;
		try {
			user = userService.getUserById(userId);
			tariffsList = tariffService.showTariffs();
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		request.setAttribute(RequestConstants.USER, user);
		request.setAttribute(RequestConstants.TARIFFS_LIST, tariffsList);

		return PageNames.EDIT_USER;
	}

}
