package by.newnet.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerForward;
import by.newnet.model.Tariff;
import by.newnet.service.ServiceFactory;
import by.newnet.service.TariffService;
import by.newnet.service.exception.ServiceException;

public class ShowAllTariffsCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		TariffService tariffService = ServiceFactory.getInstance().getTariffService();
		List<Tariff> tariffsList = null;
		try {
			tariffsList = tariffService.showTariffs();
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		request.setAttribute(RequestConstants.TARIFFS_LIST, tariffsList);
		return new ControllerForward(PageNames.TARIFFS);
	}

}
