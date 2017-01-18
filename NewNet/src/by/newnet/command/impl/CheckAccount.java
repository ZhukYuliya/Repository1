package by.newnet.command.impl;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class CheckAccount implements Command {
	private static final String ACCOUNT = "account";
	private static final String CHECK_ACCOUNT_MESSAGE = "checkAccountMessage";
	public static final String USER = "user";
	public static final Pattern CONTRACT_PATTERN = Pattern.compile("^[1-9]\\d{11}");

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String account;
		account = request.getParameter(ACCOUNT);
		String message = Validator.checkEmptyFields(account);
		if(message == null){
			message=Validator.validateContract(account);
		}
		String page = null;
		User user = null;
		//logic in command?
		if (message == null) {
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				user = userService.getUserByAccount(account);
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
			if (user != null) {
				if (user.getPassword() != null) {
					message = "account_exists";
					request.setAttribute(CHECK_ACCOUNT_MESSAGE, message);
					page = PageNames.INDEX;
				} else {
					HttpSession session = request.getSession();
					session.setAttribute(USER, user);
					page = PageNames.REGISTRATION;
				}
			}
		} else {
			request.setAttribute(CHECK_ACCOUNT_MESSAGE, message);
			page = PageNames.INDEX;
		}
		return page;
	}
}
