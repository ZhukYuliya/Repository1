package by.newnet.command.impl;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.constant.PageNames;
import by.newnet.command.constant.RequestConstants;
import by.newnet.command.exception.CommandException;
import by.newnet.command.validator.Validator;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerForward;
import by.newnet.controller.ControllerSendRedirect;
import by.newnet.model.Tariff;
import by.newnet.service.ServiceFactory;
import by.newnet.service.TariffService;
import by.newnet.service.exception.DuplicateTariffServiceException;
import by.newnet.service.exception.ServiceException;

/**
 * The Class SaveTariffCommand. Saves the updates to already existing tariffs and saves newly 
 * added tariffs, differentiating between them by a boolean field "newlyAdded".
 */
public class SaveTariffCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		boolean newlyAdded;

		String idParameter = request.getParameter(RequestConstants.ID);
		/**
		 * Learning if the tariff is new or already existing by estimating the idParameter
		 * which has come as request parameter if the tariff exists and has not come if the tariff
		 * is newly added.
		 */
		int id = 0;
		if (idParameter == null) {
			newlyAdded = true;
		} else {
			id = Integer.valueOf(idParameter);
			newlyAdded = false;
		}
		String name = request.getParameter(RequestConstants.NAME);
		String priceParameter = request.getParameter(RequestConstants.PRICE);
		String speedParameter = request.getParameter(RequestConstants.SPEED);
		String trafficParameter = request.getParameter(RequestConstants.TRAFFIC);

		ControllerAction controllerAction = null;
		String message =
		        Validator.validateTariff(name, priceParameter, speedParameter, trafficParameter);
		if (message == null) {
			BigDecimal price = new BigDecimal(request.getParameter(RequestConstants.PRICE));
			int speed = Integer.valueOf(request.getParameter(RequestConstants.SPEED));
			int traffic = Integer.valueOf(request.getParameter(RequestConstants.TRAFFIC));
			boolean inactive = Boolean.valueOf(request.getParameter(RequestConstants.INACTIVE));
			
			Tariff tariff = new Tariff();
			tariff.setId(id);
			tariff.setName(name);
			tariff.setPrice(price);
			tariff.setSpeed(speed);
			tariff.setTraffic(traffic);
			tariff.setInactive(inactive);
			TariffService TariffService = ServiceFactory.getInstance().getTariffService();
			try {
				/**
				 * Redirects the user to the page with tariffs list and notifies him that tariff
				 * was saved.
				 */
				TariffService.saveTariff(tariff, newlyAdded);
				message = RequestConstants.TARIFF_SAVED;
				controllerAction = new ControllerSendRedirect(PageNames.SHOW_TARIFFS_COMMAND + "&"
				        + RequestConstants.SAVE_TARIFF_MESSAGE + "=" + message);
			} catch (DuplicateTariffServiceException e) {
				/**
				 * If the tariff with such a name already exists, leaves the user at the same page saying
				 * the reason.
				 */
				message = RequestConstants.DUPLICATE_TARIFF_NAME;
				request.setAttribute(RequestConstants.SAVE_TARIFF_MESSAGE, message);
				controllerAction = new ControllerForward(PageNames.EDIT_TARIFF);
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		} else {
			/**
			 * If the tariff's fields validation failed, leaves the user at the same page saying
			 * what is wrong with the input.
			 */
			request.setAttribute(RequestConstants.SAVE_TARIFF_MESSAGE, message);
			controllerAction = new ControllerForward(PageNames.EDIT_TARIFF);
		}
		return controllerAction;
	}
}
