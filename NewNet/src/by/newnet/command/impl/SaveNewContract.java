package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class SaveNewContract implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String contract = request.getParameter(RequestConstants.CONTRACT);
		String firstName = request.getParameter(RequestConstants.FIRST_NAME);
		String secondName = request.getParameter(RequestConstants.SECOND_NAME);

		String message = Validator.validateNewContract(contract, firstName, secondName);
		if (message == null) {
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				userService.saveContract(contract, firstName, secondName);
				message = "contract_saved";
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		}
		request.setAttribute(RequestConstants.SAVE_CONTRACT_MESSAGE, message);
		return PageNames.NEW_CONTRACT;
	}
}
