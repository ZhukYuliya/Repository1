package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.Command;
import by.newnet.command.constant.PageNames;
import by.newnet.command.exception.CommandException;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerForward;

/**
 * The Class RegisterNewContractCommand. Forwardes operator to the page with a
 * form for inserting new contracts.
 */
public class ToNewContractCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response)
	        throws CommandException {

		return new ControllerForward(PageNames.NEW_CONTRACT);
	}
}
