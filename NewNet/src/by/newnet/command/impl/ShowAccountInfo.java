package by.newnet.command.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.Account;
import by.newnet.domain.Book;
import by.newnet.domain.User;
import by.newnet.service.TariffService;
import by.newnet.service.AccountService;
import by.newnet.service.ServiceFactory;
import by.newnet.service.exception.ServiceException;

public class ShowAccountInfo implements Command {

		public static final String ACCOUNT = "account";
		public static final String USER = "user";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		AccountService accountService = ServiceFactory.getInstance().getAccountService();
		Account account = null;
		User user = (User)request.getSession().getAttribute(USER);
		try {
			account = accountService.getUser(user);
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		request.setAttribute(ACCOUNT, account);
	
		return PageNames.HOME;
	}

}
