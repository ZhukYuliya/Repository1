package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import command.constant.PageNames;
import command.constant.RequestConstants;
import command.exception.CommandException;
import controller.ControllerAction;
import controller.ControllerSendRedirect;
import model.User;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

/**
 * The Class SubscriptionCommand. Saves a newly chosen out of tariffs list tariff for a user. 
 */
public class SubscriptionCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		int newTariffId = Integer.valueOf(request.getParameter(RequestConstants.NEW_TARIFF));
		User user = (User) request.getSession().getAttribute(RequestConstants.USER);
		int userId = user.getId();
		UserService userService = ServiceFactory.getInstance().getUserService();
		String message = null;
		try {
			userService.subscribeTariff(userId, newTariffId);
			message = RequestConstants.SUCCESSFUL_SUBSCRIPTION;
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		return new ControllerSendRedirect(PageNames.SHOW_ACCOUNT_COMMAND + "&" 
		+ RequestConstants.SUBSCRIPTION_MESSAGE + "=" + message);
	}
}
