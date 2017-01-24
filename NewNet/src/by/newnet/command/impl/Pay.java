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
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		UserService userService = ServiceFactory.getInstance().getUserService();
		int userId = ((User) request.getSession().getAttribute(RequestConstants.USER)).getId();
		String number = request.getParameter(RequestConstants.NUMBER);
		String expirationMonth = request.getParameter(RequestConstants.EXPIRATION_MONTH);
		String expirationYear = request.getParameter(RequestConstants.EXPIRATION_YEAR);
		String securityCode = request.getParameter(RequestConstants.SECURITY_CODE);
		String firstName = request.getParameter(RequestConstants.FIRST_NAME);
		String secondName = request.getParameter(RequestConstants.SECOND_NAME);
		String amountString = request.getParameter(RequestConstants.AMOUNT);
		CreditCard card;
		String message = null;
		message = Validator.validateCardDetails(number, expirationMonth, expirationYear,
		        securityCode, firstName, secondName, amountString);
		if (message == null) {
			BigDecimal amount = new BigDecimal(request.getParameter(RequestConstants.AMOUNT));
			card = new CreditCard();
			card.setNumber(number);
			card.setExpirationMonth(expirationMonth);
			card.setExpirationYear(expirationYear);
			card.setSecurityCode(securityCode);
			card.setFirstName(firstName);
			card.setSecondName(secondName);
			try {
				userService.pay(userId, card, amount);
				message = "successful_payment";
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		}
		request.setAttribute(RequestConstants.PAYMENT_MESSAGE, message);

		return PageNames.SHOW_ACCOUNT_COMMAND;
	}

}
