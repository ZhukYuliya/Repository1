package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.newnet.command.Command;
import by.newnet.command.constant.PageNames;
import by.newnet.command.constant.RequestConstants;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerSendRedirect;

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
