package by.newnet.command.impl;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.CreditCard;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class Pay implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		UserService userService = ServiceFactory.getInstance().getUserService();
		int userId = ((User)request.getSession().getAttribute(Constants.USER)).getId();
		BigDecimal amount = new BigDecimal(request.getParameter(Constants.AMOUNT));
		String message = null;
		CreditCard card = new CreditCard();
		card.setNumber(request.getParameter(Constants.NUMBER));
		card.setExpirationMonth(request.getParameter(Constants.EXPIRATION_MONTH));
		card.setExpirationYear(request.getParameter(Constants.EXPIRATION_YEAR));
		card.setSecurityCode(request.getParameter(Constants.SECURITY_CODE));
		card.setFirstName(request.getParameter(Constants.FIRST_NAME));
		card.setSecondName(request.getParameter(Constants.SECOND_NAME));

		try {
			userService.pay(userId, card, amount);
			message = "successful_payment";
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		request.setAttribute(Constants.PAYMENT_MESSAGE, message);
	
		return PageNames.SHOW_ACCOUNT_COMMAND;
	}

}
