package controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import command.constant.RequestConstants;

/**
 * The Class CharacterFilter. Sets request and response endocing to UTF-8.
 */
public class CharacterFilter implements Filter {

	/**
	 * Instantiates a new character filter.
	 */
	public CharacterFilter() {

	}
	@Override
	public void destroy() {

	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(RequestConstants.ENCODING);
		response.setCharacterEncoding(RequestConstants.ENCODING);
		chain.doFilter(request, response);
	}
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
