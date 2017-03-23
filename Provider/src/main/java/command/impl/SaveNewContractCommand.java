package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import command.constant.PageNames;
import command.constant.RequestConstants;
import command.exception.CommandException;
import command.validator.Validator;
import controller.ControllerAction;
import controller.ControllerSendRedirect;
import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;

/**
 * The Class SaveNewContractCommand. Saves a new user's contract number and name.
 */
public class SaveNewContractCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String contract = request.getParameter(RequestConstants.CONTRACT);
		String firstName = request.getParameter(RequestConstants.FIRST_NAME);
		String secondName = request.getParameter(RequestConstants.SECOND_NAME);

		String message = Validator.validateNewContract(contract, firstName, secondName);
		if (message == null) {
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				userService.saveContract(contract, firstName, secondName);
				message = RequestConstants.CONTRACT_SAVED;
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		}
		return new ControllerSendRedirect(PageNames.REGISTER_NEW_CONTRACT_COMMAND + "&"
		        + RequestConstants.SAVE_CONTRACT_MESSAGE + "=" + message);
	}
}
