package by.newnet.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import by.newnet.command.CommandName;
import by.newnet.command.impl.RequestConstants;

public class LocalizationTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String htmlContent =
	        // repeating code
			//no attributes?
	        "'<form action='${contextPath}/controller' method='post'>"
	                + "<input type='hidden' name='command' value='" + CommandName.CHANGE_LOCALE
	                + "'/>" + "<input type='hidden' name='newLocale' value='ru'/>"
	                + "<input type='submit' value='%s'/>'/></form>"

	                + "<form action='${contextPath}/controller' method='post'>"
	                + "<input type='hidden' name='command' value='" + CommandName.CHANGE_LOCALE
	                + "'/>" + "<input type='hidden' name='newLocale' value='en'/>"
	                + "<input type='submit' value='%s'/></form>";

	@Override
	public int doStartTag() throws JspException {
		try {
			Locale locale = (Locale) pageContext.getSession().getAttribute(RequestConstants.LOCALE);
			ResourceBundle resourseBundle =
			        locale == null ? ResourceBundle.getBundle("resources.localization")
			                : ResourceBundle.getBundle("resources.localization", locale);
			String ruMessage = resourseBundle.getString("localization.ru_button");
			String enMessage = resourseBundle.getString("localization.en_button");
			String htmlOutput = String.format(htmlContent, ruMessage, enMessage);
			pageContext.getOut().print(htmlOutput);
		} catch (IOException e) {
			throw new JspException(e);
			// log?
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() {
		return EVAL_PAGE;
	}
}
