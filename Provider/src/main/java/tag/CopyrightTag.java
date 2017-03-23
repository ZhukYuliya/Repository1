package tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;

/**
 * The Class CopyrightTag. Displays the copyright sign for NewNet
 */
public class CopyrightTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CopyrightTag.class);

	private String styleClass;

	/**
	 * Gets the style class.
	 *
	 * @return the style class
	 */
	public String getstyleClass() {
		return styleClass;
	}

	/**
	 * Sets the style class.
	 *
	 * @param styleClass the new style class
	 */
	public void setstyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	
	@Override
	public int doStartTag() throws JspException {
		try {
			pageContext.getOut().print("<small class='" + styleClass + "'>� 2015-2016 NewNet</small>");
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
