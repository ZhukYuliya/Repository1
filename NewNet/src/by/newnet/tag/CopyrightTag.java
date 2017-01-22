package by.newnet.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class CopyrightTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private String styleClass;

	public String getstyleClass() {
		return styleClass;
	}

	public void setstyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().print("<small class='" + styleClass + "'>© 2015-2016 NewNet</small>");
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
