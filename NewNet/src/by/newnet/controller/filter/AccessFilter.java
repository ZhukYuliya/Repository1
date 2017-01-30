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
import by.newnet.command.impl.PageNames;
import by.newnet.command.impl.RequestConstants;
import by.newnet.controller.Controller;
import by.newnet.model.User;

public class AccessFilter implements Filter {

	private final Set<String> guestCommands;
	private final Set<String> loggedUserCommands;
	private final Set<String> customerCommands;
	private final Set<String> operatorCommands;
	private final Set<String> adminCommands;

	public AccessFilter() {
		guestCommands = new HashSet<String>();
		guestCommands.add(CommandName.AUTHENTICATION.toString());
		guestCommands.add(CommandName.CHECK_ACCOUNT.toString());
		guestCommands.add(CommandName.REGISTRATION.toString());
		guestCommands.add(CommandName.CHANGE_LOCALE.toString());
		guestCommands.add(CommandName.SHOW_TARIFFS.toString());
		guestCommands.add(CommandName.POST_REQUEST.toString());

		loggedUserCommands = new HashSet<String>();
		loggedUserCommands.addAll(guestCommands);
		loggedUserCommands.add(CommandName.CHANGE_PERSONAL_DETAILS.toString());
		loggedUserCommands.add(CommandName.LOG_OUT.toString());
		loggedUserCommands.add(CommandName.SET_CONTACTS.toString());
		loggedUserCommands.add(CommandName.SET_PASSWORD.toString());
		loggedUserCommands.add(CommandName.SHOW_ACCOUNT.toString());
		loggedUserCommands.add(CommandName.SUBSCRIBE.toString());

		customerCommands = new HashSet<String>();
		customerCommands.addAll(loggedUserCommands);
		customerCommands.add(CommandName.PAYMENT.toString());
		customerCommands.add(CommandName.TO_PAYMENT.toString());

		operatorCommands = new HashSet<String>();
		operatorCommands.addAll(loggedUserCommands);
		operatorCommands.add(CommandName.REGISTER_NEW_CONTRACT.toString());
		operatorCommands.add(CommandName.SAVE_NEW_CONTRACT.toString());
		operatorCommands.add(CommandName.SET_REQUEST_STATUS.toString());
		operatorCommands.add(CommandName.SHOW_REQUESTS.toString());

		adminCommands = new HashSet<String>();
		adminCommands.addAll(loggedUserCommands);
		adminCommands.add(CommandName.SAVE_TARIFF.toString());
		adminCommands.add(CommandName.SAVE_USER.toString());
		adminCommands.add(CommandName.SHOW_USERS.toString());
		adminCommands.add(CommandName.SHOW_USER.toString());
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String command = httpRequest.getParameter(RequestConstants.COMMAND);
		if (command != null) {
			command = command.toUpperCase();
			User user = (User) httpRequest.getSession().getAttribute(RequestConstants.USER);
			if (user == null) {
				if (guestCommands.contains(command)) {
					chain.doFilter(request, response);
				} else {
					redirectToIndex(request, response);
				}
			} else {
				if(user.isAdmin() && adminCommands.contains(command)
				        || user.isOperator() && operatorCommands.contains(command)
				        || user.isCustomer() && customerCommands.contains(command)){
					chain.doFilter(request, response);
				} else {
					redirectToIndex(request, response);
				}
			}
		} else {
			redirectToIndex(request, response);
		}
	}

	private void redirectToIndex(ServletRequest request, ServletResponse response)
	        throws IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String contextPath = httpRequest.getContextPath();
		httpResponse.sendRedirect(contextPath + PageNames.INDEX);

	}

	/*
	 * if (uri.startsWith("/index.jsp") || ( &&
	 */
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
