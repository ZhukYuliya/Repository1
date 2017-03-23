package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import command.constant.PageNames;
import command.constant.RequestConstants;
import command.exception.CommandException;
import controller.ControllerAction;
import controller.ControllerForward;
import model.Tariff;
import service.ServiceFactory;
import service.TariffService;
import service.exception.ServiceException;

/**
 * The Class ShowTariffCommand. Forwards admin to the page with a form for tariff editing/adding.
 */
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
