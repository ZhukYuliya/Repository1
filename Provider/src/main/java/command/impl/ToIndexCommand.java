package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.Command;
import command.constant.PageNames;
import command.exception.CommandException;
import controller.ControllerAction;
import controller.ControllerSendRedirect;

/**
 * The Class ToIndexCommand. Redirects a user to index page.
 */
public class ToIndexCommand implements Command {

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		return new ControllerSendRedirect(PageNames.INDEX);
	}

}
