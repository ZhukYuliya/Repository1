package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class Registration implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String password;
		String reenterPassword;
		String phone;
		String email;
		
		int userId = ((User)request.getSession().getAttribute(Constants.USER)).getId();
		password = request.getParameter(Constants.PASSWORD);
		reenterPassword = request.getParameter(Constants.REENTER_PASSWORD);
		phone = request.getParameter(Constants.PHONE);
		email = request.getParameter(Constants.EMAIL);
		String message = Validator.validateRegistration(password, reenterPassword, phone, email);
		String page = null;
		if (message == null) {
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				userService.register(userId, password, reenterPassword, phone, email);
				message = "successful_registration";
				page = PageNames.INDEX;
			} catch (ServiceException e) {
				// exception?message needed? message registration failed
				message = "??";
				throw new CommandException(e);
			}
		}else{
			page = PageNames.REGISTRATION;
		}
		request.setAttribute(Constants.REGISTRATION_MESSAGE, message);
		return page;
	}
}
