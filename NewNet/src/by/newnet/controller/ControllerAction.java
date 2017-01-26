package by.newnet.controller;

public abstract class ControllerAction {
	private String url;
	public ControllerAction(String url) {
		this.url = url;
	}
	public String getUrl() {
		return url;
	}
}
