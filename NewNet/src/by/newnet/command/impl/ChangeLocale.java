package by.newnet.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;

public class ChangeLocale implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		String locale = null;
		
		locale = request.getParameter("newLocale");
		
		request.getSession(true).setAttribute("locale", locale);
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user")!= null){
			return PageNames.HOME;
		}else{
			return PageNames.INDEX;
		}
	}

}
