package by.newnet.controller;

/**
 * The Class ControllerAction.
 */
public abstract class ControllerAction {
	private final String url;
	
	/**
	 * Instantiates a new controller action.
	 *
	 * @param url the url
	 */
	public ControllerAction(String url) {
		this.url = url;
	}
	
	/**
	 * Gets the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
}
