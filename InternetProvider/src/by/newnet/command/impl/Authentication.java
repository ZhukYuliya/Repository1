package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class Authentication implements Command {

	private static final String LOGIN = "login";	
	private static final String PASSWORD = "password";
	private static final String LOGIN_FAILED = "loginFailed";
	public static final String USER = "user";
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException{
		
		String login;
		String password;
		
		login = request.getParameter(LOGIN);
		password = request.getParameter(PASSWORD);
		
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		
		UserService userService = ServiceFactory.getInstance().getUserService();
		User loggedUser = null;
		try {
			loggedUser = userService.logination(user);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		
		
		if (loggedUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute(USER, loggedUser);
			return PageNames.HOME;
		} else {
			request.setAttribute(LOGIN_FAILED, true);
			return PageNames.INDEX;
				
		}
	}

}

