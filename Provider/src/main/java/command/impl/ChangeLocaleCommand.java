package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import command.constant.PageNames;
import command.constant.RequestConstants;
import command.exception.CommandException;
import controller.ControllerAction;
import controller.ControllerSendRedirect;

/**
 * The Class ChangeLocaleCommand. Changes the locale in user's session and redirects him to the last
 * GET request he made (only GET to avoid problems caused with POST non-idempotence)  
 */
public class ChangeLocaleCommand implements Command{

	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		String locale = request.getParameter(RequestConstants.NEW_LOCALE);
		HttpSession session = request.getSession();
		session.setAttribute(RequestConstants.LOCALE, locale);
		String url = (String)session.getAttribute(RequestConstants.PREVIOUS_GET_REQUEST_URL);
		if(url == null){
			url = PageNames.TO_INDEX_COMMAND;
		}
		return new ControllerSendRedirect(url);
	}

}
