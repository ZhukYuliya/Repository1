package command.impl;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import command.constant.PageNames;
import command.constant.RequestConstants;
import command.exception.CommandException;
import command.validator.Validator;
import controller.ControllerAction;
import controller.ControllerForward;
import controller.ControllerSendRedirect;
import model.Tariff;
import service.ServiceFactory;
import service.TariffService;
import service.exception.DuplicateTariffServiceException;
import service.exception.ServiceException;

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
		/*
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
				/*
				 * Redirects the user to the page with tariffs list and notifies him that tariff
				 * was saved.
				 */
				TariffService.saveTariff(tariff, newlyAdded);
				message = RequestConstants.TARIFF_SAVED;
				controllerAction = new ControllerSendRedirect(PageNames.SHOW_TARIFFS_COMMAND + "&"
				        + RequestConstants.SAVE_TARIFF_MESSAGE + "=" + message);
			} catch (DuplicateTariffServiceException e) {
				/*
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
			/*
			 * If the tariff's fields validation failed, leaves the user at the same page saying
			 * what is wrong with the input.
			 */
			request.setAttribute(RequestConstants.SAVE_TARIFF_MESSAGE, message);
			controllerAction = new ControllerForward(PageNames.EDIT_TARIFF);
		}
		return controllerAction;
	}
}
