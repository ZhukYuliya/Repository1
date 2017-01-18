package by.newnet.command.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;
import by.newnet.domain.User;
import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class AddNewContract implements Command {

	private static final String CONTRACT = "contract";
	private static final String FIRST_NAME = "firstName";
	private static final String SECOND_NAME = "secondName";
	private static final String ADD_CONTRACT_MESSAGE = "addContractMessage";
	
	public static final Pattern CONTRACT_PATTERN =
	        Pattern.compile("^[1-9]\\d{11}");
	public static final Pattern NAME_PATTERN =
	        Pattern.compile("^[A-Z][a-z]{1,40}");

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		String contract = request.getParameter(CONTRACT);
		String firstName = request.getParameter(FIRST_NAME);
		String secondName = request.getParameter(SECOND_NAME);
	
		String message = validation(contract, firstName, secondName);

		if (message == null) {

			UserService userService = ServiceFactory.getInstance().getUserService();

			try {
				userService.addContract(contract, firstName, secondName);
			} catch (ServiceException e) {
				throw new CommandException(e);
			}
			request.setAttribute(ADD_CONTRACT_MESSAGE,message);
		}
		return PageNames.INDEX;

	}

	private String validation(String contract, String firstName, String secondName) {
		if (StringUtils.isEmpty(contract) ||StringUtils.isEmpty(firstName)||StringUtils.isEmpty(secondName)) {
			return "empty_fields";
		}
		Matcher contractMatcher = CONTRACT_PATTERN.matcher(contract);
		if (!contractMatcher.matches()) {
			return "incorrect_account_number";
		}
		Matcher firstNameMatcher = NAME_PATTERN.matcher(firstName);
		Matcher secondNameMatcher = NAME_PATTERN.matcher(secondName);
		if (!firstNameMatcher.matches() || !secondNameMatcher.matches()) {
			return "incorrect_name";
		}
		return null;
	}
}
