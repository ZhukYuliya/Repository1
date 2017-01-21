package by.newnet.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.Tariff;
import by.newnet.service.TariffService;
import by.newnet.service.ServiceFactory;
import by.newnet.service.exception.ServiceException;

public class ShowTariffs implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		TariffService tariffService = ServiceFactory.getInstance().getTariffService();
		List<Tariff> tariffsList = null;
		try {
			tariffsList = tariffService.showTariffs();
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		request.setAttribute(Constants.TARIFFS_LIST, tariffsList);
		return PageNames.TARIFFS;
	}

}
