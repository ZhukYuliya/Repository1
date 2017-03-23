package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import command.constant.PageNames;
import command.exception.CommandException;
import controller.ControllerAction;
import controller.ControllerForward;

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
