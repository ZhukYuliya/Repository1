package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerForward;
import by.newnet.controller.ControllerSendRedirect;
import by.newnet.model.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class RegistrationCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {
		ControllerAction controllerAction = null;

		String password;
		String reenterPassword;
		String phone;
		String email;
		
		int userId = Integer.valueOf((request.getParameter(RequestConstants.USER_ID)));
		password = request.getParameter(RequestConstants.PASSWORD);
		reenterPassword = request.getParameter(RequestConstants.REENTER_PASSWORD);
		phone = request.getParameter(RequestConstants.PHONE);
		email = request.getParameter(RequestConstants.EMAIL);
		String message = Validator.validateRegistration(password, reenterPassword, phone, email);
		String page = null;
		if (message == null) {
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				userService.register(userId, password, phone, email);
				message = "successful_registration";
				page = PageNames.INDEX;
				controllerAction = new ControllerSendRedirect(page + "?" 
				+ RequestConstants.REGISTRATION_MESSAGE + "=" + message);
			} catch (ServiceException e) {
				// exception?message needed? message registration failed
				message = "??";
				throw new CommandException(e);
			}
		}else{
			User user = new User();
			user.setId(userId);
			user.setSecondName(request.getParameter(RequestConstants.SECOND_NAME));
			user.setFirstName(request.getParameter(RequestConstants.FIRST_NAME));
			request.setAttribute(RequestConstants.USER, user);
			request.setAttribute(RequestConstants.REGISTRATION_MESSAGE, message);
			page = PageNames.REGISTRATION;
			controllerAction = new ControllerForward(page);
		}
		return controllerAction;
	}
}
