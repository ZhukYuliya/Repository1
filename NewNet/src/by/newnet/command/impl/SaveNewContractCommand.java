package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.constant.PageNames;
import by.newnet.command.constant.RequestConstants;
import by.newnet.command.exception.CommandException;
import by.newnet.command.validator.Validator;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerSendRedirect;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

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
