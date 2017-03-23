package command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import command.Command;
import command.constant.PageNames;
import command.constant.RequestConstants;
import controller.ControllerAction;
import controller.ControllerSendRedirect;

/**
 * The Class LogOutCommand. Invalidates session of a user and redirects him to index page.
 */
public class LogOutCommand implements Command {
	
	@Override
	public ControllerAction execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		if (session.getAttribute(RequestConstants.USER)!= null){
			session.invalidate();
		}
		return new ControllerSendRedirect(PageNames.INDEX);
}
}
