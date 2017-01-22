package by.newnet.command.impl;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import by.newnet.command.Command;
import by.newnet.command.exception.CommandException;

public class ChangeLocale implements Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
		
		String locale = null;
		locale = request.getParameter(RequestConstants.NEW_LOCALE);
		request.getSession(true).setAttribute(RequestConstants.LOCALE, locale);
		//check!!!
		//HttpSession session = request.getSession();
		try {
			response.sendRedirect(request.getRequestURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		/*if(session.getAttribute(Constants.USER)!= null){
			return PageNames.HOME;
		}else{
			return PageNames.INDEX;
		}*/
	}

}
