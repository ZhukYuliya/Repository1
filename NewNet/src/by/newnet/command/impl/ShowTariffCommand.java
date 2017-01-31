package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.constant.PageNames;
import by.newnet.command.constant.RequestConstants;
import by.newnet.command.exception.CommandException;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerForward;
import by.newnet.model.Tariff;
import by.newnet.service.ServiceFactory;
import by.newnet.service.TariffService;
import by.newnet.service.exception.ServiceException;

public class ShowTariffCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		TariffService TariffService = ServiceFactory.getInstance().getTariffService();
		int tariffId = 0;
		Tariff tariff = null;
		if (request.getParameter(RequestConstants.ID) != null) {
			tariffId = Integer.valueOf(request.getParameter(RequestConstants.ID));
			try {
				tariff = TariffService.getTariffById(tariffId);
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
			request.setAttribute(RequestConstants.TARIFF, tariff);
		}
		return new ControllerForward(PageNames.EDIT_TARIFF);
	}

}
