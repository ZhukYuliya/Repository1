package by.newnet.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import by.newnet.command.impl.PageNames;
import by.newnet.command.impl.RequestConstants;

public class PreviousRequestFilter implements Filter {

	private static final String ENCODING = "utf-8";

	public PreviousRequestFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(ENCODING);
		response.setCharacterEncoding(ENCODING);
		HttpServletRequest request1 = (HttpServletRequest) request;
		if (request1.getMethod().equals("GET") && !request1.getRequestURL().toString().endsWith(PageNames.JS_MESSAGES)) {
			HttpSession session = request1.getSession();
			StringBuffer url = new StringBuffer(request1.getRequestURI().substring(request1.getContextPath().length()));
			if (request1.getQueryString() != null){
				url.append("?").append(request1.getQueryString()).toString();
			}
			session.setAttribute(RequestConstants.PREVIOUS_GET_REQUEST_URL, url.toString());
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
