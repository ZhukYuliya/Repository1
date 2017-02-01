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

import org.apache.log4j.Logger;

import by.newnet.command.CommandName;
import by.newnet.command.constant.PageNames;
import by.newnet.command.constant.RequestConstants;
import by.newnet.model.User;

/**
 * The Class AccessFilter. Authorizated users to invoke different command.
 */
public class AccessFilter implements Filter {

	private static final Logger logger = Logger.getLogger(AccessFilter.class);

	private final Set<String> guestCommands;
	private final Set<String> loggedUserCommands;
	private final Set<String> customerCommands;
	private final Set<String> operatorCommands;
	private final Set<String> adminCommands;

	/**
	 * Instantiates a new access filter.
	 */
	public AccessFilter() {
		guestCommands = new HashSet<String>();
		guestCommands.add(CommandName.AUTHENTICATION.toString());
		guestCommands.add(CommandName.CHECK_ACCOUNT.toString());
		guestCommands.add(CommandName.REGISTRATION.toString());
		guestCommands.add(CommandName.CHANGE_LOCALE.toString());
		guestCommands.add(CommandName.SHOW_TARIFFS.toString());
		guestCommands.add(CommandName.POST_REQUEST.toString());
		guestCommands.add(CommandName.TO_INDEX.toString());
		guestCommands.add(CommandName.TO_REGISTRATION.toString());

		loggedUserCommands = new HashSet<String>();
		loggedUserCommands.addAll(guestCommands);
		loggedUserCommands.add(CommandName.TO_PERSONAL_DETAILS.toString());
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
		operatorCommands.add(CommandName.TO_NEW_CONTRACT.toString());
		operatorCommands.add(CommandName.SAVE_NEW_CONTRACT.toString());
		operatorCommands.add(CommandName.SET_REQUEST_STATUS.toString());
		operatorCommands.add(CommandName.SHOW_REQUESTS.toString());

		adminCommands = new HashSet<String>();
		adminCommands.addAll(loggedUserCommands);
		adminCommands.add(CommandName.SAVE_TARIFF.toString());
		adminCommands.add(CommandName.SAVE_USER.toString());
		adminCommands.add(CommandName.SHOW_USERS.toString());
		adminCommands.add(CommandName.SHOW_USER.toString());
		adminCommands.add(CommandName.SHOW_TARIFF.toString());
	}
	
	@Override
	public void destroy() {

	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String command = httpRequest.getParameter(RequestConstants.COMMAND);
		if (command != null) {
			command = command.toUpperCase();
			User user = (User) httpRequest.getSession().getAttribute(RequestConstants.USER);
			if (user == null) {
				/*
				 * Checks if a not logged in user can invoke a certain command.
				 */
				if (guestCommands.contains(command)) {
					chain.doFilter(request, response);
				} else {
					redirectToIndex(request, response);
					logger.debug("Guest user tried to reach command " + command
					        + " and was redirected to index.jsp");
				}
			} else {
				/**
				 * Checks if a logged in user can invoke a certain command taking into consideration 
				 * his role.
				 */
				if (user.isAdmin() && adminCommands.contains(command)
				        || user.isOperator() && operatorCommands.contains(command)
				        || user.isCustomer() && customerCommands.contains(command)) {
					chain.doFilter(request, response);
				} else {
					redirectToIndex(request, response);
					logger.debug("User " + user.getId() + " tried to reach command " + command
					        + " and was redirected to index.jsp");
				}
			}
		} else {
			redirectToIndex(request, response);
		}
	}

	/**
	 * Redirect to index. It is used to redirect users to index page after they have tried to invoke 
	 * a command they are not authorized for.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void redirectToIndex(ServletRequest request, ServletResponse response)
	        throws IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String contextPath = httpRequest.getContextPath();
		httpResponse.sendRedirect(contextPath + PageNames.INDEX);

	}
	
	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
