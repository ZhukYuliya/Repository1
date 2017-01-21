package by.newnet.controller.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.newnet.command.CommandName;
import by.newnet.command.impl.Authentication;
import by.newnet.command.impl.Constants;
import by.newnet.controller.Controller;

public class AccessFilter implements Filter {
	private static final String CONTROLLER = "/controller";
	
	private Set<String> allowedCommand;
	

    public AccessFilter() {
    	allowedCommand = new HashSet<String>();
    	allowedCommand.add(CommandName.AUTHENTICATION.toString());
    	allowedCommand.add(CommandName.REGISTRATION.toString());
    	allowedCommand.add(CommandName.CHANGE_LOCALE.toString());
    }

	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		if( httpRequest.getSession().getAttribute(Constants.USER)!= null){
			chain.doFilter(request, response);
		}else{
			String uri = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
			String command = httpRequest.getParameter(Controller.COMMAND);
			if (command != null) {
				command = command.toUpperCase();
			}
			
			if (uri.startsWith("/index.jsp")|| (uri.startsWith(CONTROLLER) &&allowedCommand.contains(command)) ){
				chain.doFilter(request, response);
			}else {
				((HttpServletResponse) response).sendRedirect("index.jsp");
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
