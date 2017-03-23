package controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import command.constant.PageNames;
import command.constant.RequestConstants;

/**
 * The Class PreviousRequestFilter. Saves in session the last GET request that was made by a user 
 * for localization purposes.
 */
public class PreviousRequestFilter implements Filter {

	/**
	 * Instantiates a new previous request filter.
	 */
	public PreviousRequestFilter() {
	}
	@Override
	public void destroy() {
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (httpRequest.getMethod().equals("GET") && !httpRequest.getRequestURL().toString().endsWith(PageNames.JS_MESSAGES)) {
			HttpSession session = httpRequest.getSession();
			StringBuffer url = new StringBuffer(httpRequest.getRequestURI().substring(httpRequest.getContextPath().length()));
			if (httpRequest.getQueryString() != null){
				url.append("?").append(httpRequest.getQueryString()).toString();
				session.setAttribute(RequestConstants.PREVIOUS_GET_REQUEST_URL, url.toString());
			}
		}
		chain.doFilter(request, response);
	}
	@Override
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
