package by.newnet.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import by.newnet.command.CommandName;
import by.newnet.command.constant.RequestConstants;

/**
 * The Class LocalizationTag. Displays 2 buttons for switching the page's language from Russian to 
 * English and vice versa.
 */
public class LocalizationTag extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LocalizationTag.class);

	public final static String htmlContent =
	        "<form action='%s/controller' method='post'>"
	                + "<input type='hidden' name='command' value='" + CommandName.CHANGE_LOCALE
	                + "'/>" + "<input type='hidden' name='newLocale' value='ru'/>"
	                + "<input type='submit' value='%s'/></form>"
	                + "<form action='%s/controller' method='post'>"
	                + "<input type='hidden' name='command' value='" + CommandName.CHANGE_LOCALE
	                + "'/>" + "<input type='hidden' name='newLocale' value='en'/>"
	                + "<input type='submit' value='%s'/></form>";

	@Override
	public int doStartTag() throws JspException {
		try {
			String locale = (String)pageContext.getSession().getAttribute(RequestConstants.LOCALE);
			ResourceBundle resourseBundle =
			        locale == null ? ResourceBundle.getBundle("resources.localization")
			                : ResourceBundle.getBundle("resources.localization", new Locale(locale));
			String ruMessage = resourseBundle.getString("localization.ru_button");
			String enMessage = resourseBundle.getString("localization.en_button");
			String contextPath = ((HttpServletRequest)pageContext.getRequest()).getContextPath();
			String htmlOutput = String.format(htmlContent, contextPath, ruMessage, contextPath, enMessage);
			pageContext.getOut().print(htmlOutput);
		} catch (IOException e) {
			logger.error("Exception was thrown when trying to execute method doStartTag", e);
			throw new JspException(e);

		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() {
		return EVAL_PAGE;
	}
}
