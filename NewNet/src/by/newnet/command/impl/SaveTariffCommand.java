package by.newnet.command.impl;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.Tariff;
import by.newnet.service.TariffService;
import by.newnet.service.ServiceFactory;
import by.newnet.service.exception.DuplicateTariffServiceException;
import by.newnet.service.exception.ServiceException;

public class SaveTariffCommand implements Command {

	// TODO: change to set tariff and use both for adding and changing tariff?
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String idParameter;
		String name;
		String priceParameter;
		String speedParameter;
		String trafficParameter;

		boolean newlyAdded;

		idParameter = request.getParameter(RequestConstants.ID);
		int id = 0;
		if (idParameter == null) {
			newlyAdded = true;
		} else {
			id = Integer.valueOf(idParameter);
			newlyAdded = false;
		}
		name = request.getParameter(RequestConstants.NAME);
		priceParameter = request.getParameter(RequestConstants.PRICE);
		speedParameter = request.getParameter(RequestConstants.SPEED);
		trafficParameter = request.getParameter(RequestConstants.TRAFFIC);
		String page = null;
		String message =
		        Validator.validateTariff(name, priceParameter, speedParameter, trafficParameter);
		if (message == null) {
			BigDecimal price = null;
			int speed = 0;
			int traffic = 0;
			boolean inactive = false;
			try {
				price = new BigDecimal(request.getParameter(RequestConstants.PRICE));
				speed = Integer.valueOf(request.getParameter(RequestConstants.SPEED));
				traffic = Integer.valueOf(request.getParameter(RequestConstants.TRAFFIC));
				inactive = Boolean.valueOf(request.getParameter(RequestConstants.INACTIVE));
			} catch (NumberFormatException e) {
				// needed? its already validated
				message = "incorrect input";
			}

			Tariff tariff = new Tariff();
			tariff.setId(id);
			tariff.setName(name);
			tariff.setPrice(price);
			tariff.setSpeed(speed);
			tariff.setTraffic(traffic);
			tariff.setInactive(inactive);
			TariffService TariffService = ServiceFactory.getInstance().getTariffService();

			try {
				TariffService.saveTariff(tariff, newlyAdded);
				message = "tariff_saved";
				page = PageNames.SHOW_TARIFFS_COMMAND;
			} catch (DuplicateTariffServiceException e) {
				message = "duplicate_tariff_name";
				page = PageNames.EDIT_TARIFF;
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		} else{
			page = PageNames.EDIT_TARIFF;
		}
		request.setAttribute(RequestConstants.SAVE_TARIFF_MESSAGE, message);
		return page;
	}
}
