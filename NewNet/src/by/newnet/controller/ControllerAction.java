package by.newnet.controller;

public abstract class ControllerAction {
	private final String url;
	public ControllerAction(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
}
