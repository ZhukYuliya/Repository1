package by.newnet.controller.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class ProjectRequestListener implements ServletRequestListener {

	public ProjectRequestListener() {

	}

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		//ignore post
			}

}
