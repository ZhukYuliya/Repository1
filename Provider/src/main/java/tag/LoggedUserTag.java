package tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

import command.CommandName;
import command.constant.RequestConstants;
import model.User;

/**
 * The Class LoggedUserTag. For a logged user displays "home" and "logout" buttons and 
 * displays the name under which the user is logged in.
 */
public class LoggedUserTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoggedUserTag.class);

	public final static String htmlContent = "<div class='right'><span>%s %s %s</span>" 
					+ "<form action='%s/controller' method='get'>"
	                + "<input type='hidden' name='command' value='" + CommandName.SHOW_ACCOUNT + "' /> "
	                + "<input type='submit' value='%s' /></form>"
	                + "<form action='%s/controller' method='get'>"
	                + "<input type='hidden' name='command' value='" + CommandName.LOG_OUT + "' /> "
	                + "<input type='submit' value='%s' /></form></div>";

	@Override
	public int doStartTag() throws JspException {
		User user = (User) pageContext.getSession().getAttribute(RequestConstants.USER);
		if (user != null) {
			try {
				String locale =
				        (String) pageContext.getSession().getAttribute(RequestConstants.LOCALE);
				ResourceBundle resourseBundle =
				        locale == null ? ResourceBundle.getBundle("resources.localization")
				                : ResourceBundle.getBundle("resources.localization", new Locale(locale));
				String userNameMessage = resourseBundle.getString("user_name");
				String homeMessage = resourseBundle.getString("home");
				String logOutMessage = resourseBundle.getString("logOut");
				String contextPath = ((HttpServletRequest)pageContext.getRequest()).getContextPath();
				String htmlOutput = String.format(htmlContent, userNameMessage, user.getFirstName(),
				        user.getSecondName(), contextPath, homeMessage, contextPath, logOutMessage);

				pageContext.getOut().print(htmlOutput);
			} catch (IOException e) {
				logger.error("Exception was thrown when trying to execute method doStartTag", e);
				throw new JspException(e);
			}
		}
		return SKIP_BODY;
	}

	@Override
	public int doEndTag() {
		return EVAL_PAGE;
	}
}
