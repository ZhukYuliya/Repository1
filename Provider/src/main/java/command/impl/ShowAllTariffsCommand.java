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
import service.ServiceFactory;
import service.TariffService;
import service.exception.ServiceException;

/**
 * The Class ShowAllTariffsCommand. Shows the list of all the tariffs of NewNet. 
 */
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
