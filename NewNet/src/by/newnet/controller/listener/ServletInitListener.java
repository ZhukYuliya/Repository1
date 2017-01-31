package by.newnet.controller.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import by.newnet.dao.jdbc.pool.ConnectionPool;
import by.newnet.dao.jdbc.pool.ConnectionPoolException;
import by.newnet.timer.DailyFeeScheduler;

public class ServletInitListener implements ServletContextListener {
	private static final Logger logger = Logger.getLogger(ServletInitListener.class);

	public ServletInitListener() {
	}

	// can a field be in listener
	DailyFeeScheduler scheduler;

	public void contextInitialized(ServletContextEvent event) {
		try {
			ConnectionPool.getInstance().initPoolData();

		} catch (ConnectionPoolException e) {
			logger.error("Exception was thrown when trying to initialize connection pool", e);
			throw new RuntimeException(e);
		}
		//comment
		scheduler = new DailyFeeScheduler();
		scheduler.startTask();
//comment
		ServletContext context = event.getServletContext();
		System.setProperty("rootPath", context.getRealPath("/"));
	}

	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			ConnectionPool.getInstance().dispose();
		} catch (ConnectionPoolException e) {
			logger.error("Exception was thrown when trying to dispose connection pool", e);
		}
		// close scheduler
		scheduler.stopTask();
	}

}
