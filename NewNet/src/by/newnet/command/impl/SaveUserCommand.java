package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerSendRedirect;
import by.newnet.domain.Tariff;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class SaveUserCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {
	
		String account;
		String firstName;
		String secondName;
		String phone;
		String email;
		
		int id = Integer.valueOf(request.getParameter(RequestConstants.ID));
		account = request.getParameter(RequestConstants.ACCOUNT);
		firstName = request.getParameter(RequestConstants.FIRST_NAME);
		secondName = request.getParameter(RequestConstants.SECOND_NAME);
		phone = request.getParameter(RequestConstants.PHONE);
		email = request.getParameter(RequestConstants.EMAIL);
		//check if epty tariff id, role id, banned?
		String message = Validator.validateSaveUser(account, firstName,secondName, phone, email);
		String page = null;
		if (message == null) {
			User user = new User();
			user.setId(id);
			user.setAccount(account);
			user.setFirstName(firstName);
			user.setSecondName(secondName);
			user.setPhone(phone);
			user.setEmail(email);
			/*Role role = new Role();
			role.setId(Integer.valueOf(request.getParameter(RequestConstants.ROLE)));
			user.setRole(role);*/
			Tariff tariff = new Tariff();
			tariff.setId(Integer.valueOf(request.getParameter(RequestConstants.TARIFF)));
			user.setTariff(tariff);
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				userService.saveUser(user);
				message = "successful_user_editing";
				page = PageNames.SHOW_CUSTOMERS_COMMAND;
			} catch (ServiceException e) {
				// exception?message needed? message registration failed
				message = "??";
				throw new CommandException(e);
			}
		}else{
			//send redirect to the same editing page?
			page = PageNames.EDIT_USER;
		}
		request.setAttribute(RequestConstants.USER_EDITING_MESSAGE, message);
		return new ControllerSendRedirect(page);
	}
}
