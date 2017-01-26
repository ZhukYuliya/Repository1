package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.newnet.command.Command;
import by.newnet.controller.ControllerAction;
import by.newnet.controller.ControllerSendRedirect;

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
