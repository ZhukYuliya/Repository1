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

		public static final String USER = "user";
		public static final String AMOUNT = "amount";
		public static final String NUMBER = "number";
		public static final String EXPIRATION_MONTH = "expirationMonth";
		public static final String EXPIRATION_YEAR = "expirationYear";
		public static final String SECURITY_CODE = "securityCode";
		public static final String FIRST_NAME = "firstName";
		public static final String SECOND_NAME = "secondName";
		public static final String PAYMENT_MESSAGE = "paymentMessage";


	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		UserService userService = ServiceFactory.getInstance().getUserService();
		int userId = ((User)request.getSession().getAttribute(USER)).getId();
		BigDecimal amount = new BigDecimal(request.getParameter(AMOUNT));
		String message = null;
		CreditCard card = new CreditCard();
		card.setNumber(request.getParameter(NUMBER));
		card.setExpirationMonth(request.getParameter(EXPIRATION_MONTH));
		card.setExpirationYear(request.getParameter(EXPIRATION_YEAR));
		card.setSecurityCode(request.getParameter(SECURITY_CODE));
		card.setFirstName(request.getParameter(FIRST_NAME));
		card.setSecondName(request.getParameter(SECOND_NAME));

		try {
			userService.pay(userId, card, amount);
			message = "successful_payment";
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		request.setAttribute(PAYMENT_MESSAGE, message);
	
		return PageNames.SHOW_ACCOUNT_COMMAND;
	}

}
