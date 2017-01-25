package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.Tariff;
import by.newnet.service.ServiceFactory;
import by.newnet.service.TariffService;
import by.newnet.service.exception.ServiceException;

public class ShowTariffCommand implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		TariffService TariffService = ServiceFactory.getInstance().getTariffService();
		int tariffId = Integer.valueOf(request.getParameter(RequestConstants.ID));
		Tariff tariff = null;
		try {
			tariff = TariffService.getTariffById(tariffId);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		request.setAttribute(RequestConstants.TARIFF, tariff);
	
		return PageNames.EDIT_TARIFF;
	}

}
