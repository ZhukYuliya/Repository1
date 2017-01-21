package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class AddNewContract implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String contract = request.getParameter(Constants.CONTRACT);
		String firstName = request.getParameter(Constants.FIRST_NAME);
		String secondName = request.getParameter(Constants.SECOND_NAME);
	
		String message = Validator.validateNewContract(contract, firstName, secondName);
		if (message == null) {
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				userService.addContract(contract, firstName, secondName);
				message = "contract_added";
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
			request.setAttribute(Constants.ADD_CONTRACT_MESSAGE,message);
		}
		return PageNames.SHOW_REQUESTS_COMMAND;
	}
}
