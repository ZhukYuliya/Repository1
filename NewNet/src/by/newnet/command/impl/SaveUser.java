package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.Role;
import by.newnet.domain.Tariff;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class SaveUser implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {
	
		String account;
		String firstName;
		String secondName;
		String phone;
		String email;
		
		int id = Integer.valueOf(request.getParameter(Constants.ID));
		account = request.getParameter(Constants.ACCOUNT);
		firstName = request.getParameter(Constants.FIRST_NAME);
		secondName = request.getParameter(Constants.SECOND_NAME);
		phone = request.getParameter(Constants.PHONE);
		email = request.getParameter(Constants.EMAIL);
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
			Role role = new Role();
			role.setId(Integer.valueOf(request.getParameter(Constants.ROLE)));
			user.setRole(role);
			Tariff tariff = new Tariff();
			tariff.setId(Integer.valueOf(request.getParameter(Constants.TARIFF)));
			user.setTariff(tariff);
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				userService.saveUser(user);
				message = "successful_user_editing";
				page = PageNames.SHOW_USERS_COMMAND;
			} catch (ServiceException e) {
				// exception?message needed? message registration failed
				message = "??";
				throw new CommandException(e);
			}
		}else{
			//send redirect to the same editing page?
			page = PageNames.EDIT_USER;
		}
		request.setAttribute(Constants.USER_EDITING_MESSAGE, message);
		return page;
	}
}
