package by.newnet.tag;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import by.newnet.command.impl.RequestConstants;
import by.newnet.domain.User;

public class HeaderTag extends TagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String htmlContent = "<div class='right'><span>%s %s %s</span>"
			+"<form action='controller' method='get'>"
			+"<input type='hidden' name='command' value='show_account' /> "
			+"<input type='submit' value='%s' /></form>"
			+"<form action='controller' method='get'>"
			+"<input type='hidden' name='command' value='log_out' /> "
			+"<input type='submit' value='%s' /></form></div>";
			
			@Override
public int doStartTag() throws JspException {
	User user = (User)pageContext.getSession().getAttribute(RequestConstants.USER);
	if(user != null){
	try {
		Locale locale = (Locale)pageContext.getSession().getAttribute(RequestConstants.LOCALE);
		ResourceBundle resourseBundle = locale == null ? 
				ResourceBundle.getBundle("resources.localization")
				: ResourceBundle.getBundle("resources.localization", locale);
		String userNameMessage = resourseBundle.getString("user_name");
		String homeMessage = resourseBundle.getString("home");
		String logOutMessage = resourseBundle.getString("logOut");
		String htmlOutput = String.format(htmlContent, userNameMessage, 
				user.getFirstName(), user.getSecondName(), homeMessage, logOutMessage);
				
		pageContext.getOut().print(htmlOutput);
	} catch (IOException e) {
		throw new JspException(e);
		// log?
	}
	}
	return SKIP_BODY;
}
	
@Override
public int doEndTag() {
	return EVAL_PAGE;
}
}
