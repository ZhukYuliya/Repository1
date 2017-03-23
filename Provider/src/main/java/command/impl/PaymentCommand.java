package command.impl;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import command.constant.PageNames;
import command.constant.RequestConstants;
import command.exception.CommandException;
import command.validator.Validator;
import controller.ControllerAction;
import controller.ControllerForward;
import controller.ControllerSendRedirect;
import model.CreditCard;
import model.User;
import service.ServiceFactory;
import service.UserService;
import service.exception.FailedPaymentServiceException;
import service.exception.ServiceException;

/**
 * The Class PaymentCommand. Validates the card details provided by a user and
 * increments him account balance.
 */
public class PaymentCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
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
		ControllerAction controllerAction = null;
		String message = null;

		message = Validator.validateCardDetails(number, expirationMonth, expirationYear,
		        securityCode, firstName, secondName, amountString);

		if (message != null) {
			/*
			 * Leaves the user at payment page showing message about wrong card
			 * details in case their validation failed.
			 */
			request.setAttribute(RequestConstants.PAYMENT_MESSAGE, message);
			controllerAction = new ControllerForward(PageNames.PAYMENT);
		} else {
			BigDecimal amount = new BigDecimal(request.getParameter(RequestConstants.AMOUNT));
			card = new CreditCard();
			card.setNumber(number);
			card.setExpirationMonth(expirationMonth);
			card.setExpirationYear(expirationYear);
			card.setSecurityCode(securityCode);
			card.setFirstName(firstName);
			card.setSecondName(secondName);
			try {
				/*
				 * Tries to execute the payment and redirects the user to his
				 * home page showing updated account balance in case the payment
				 * was allowed by the bank.
				 */
				userService.pay(userId, card, amount);
				message = RequestConstants.SUCCESSFUL_PAYMENT;
				controllerAction = new ControllerSendRedirect(PageNames.SHOW_ACCOUNT_COMMAND + "&"
				        + RequestConstants.PAYMENT_MESSAGE + "=" + message);
			} catch (FailedPaymentServiceException e) {
				/*
				 * In case of failed payment leaves the user at the same page saying why 
				 * the payment failed.
				 */
				message = e.getMessage();
				request.setAttribute(RequestConstants.PAYMENT_MESSAGE, message);
				controllerAction = new ControllerForward(PageNames.PAYMENT);
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
		}
		return controllerAction;
	}
}
