package controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

import dao.jdbc.pool.ConnectionPool;
import dao.jdbc.pool.ConnectionPoolException;
import timer.DailyFeeScheduler;

/**
 * The listener interface for receiving servletInit events. The class that is
 * interested in processing a servletInit event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addServletInitListener<code> method. When the servletInit
 * event occurs, that object's appropriate method is invoked.
 *
 * @see ServletInitEvent
 */
public class ServletInitListener implements ServletContextListener {
	private final Logger logger = Logger.getLogger(ServletInitListener.class);

	/**
	 * Instantiates a new servlet init listener.
	 */
	public ServletInitListener() {
	}

	DailyFeeScheduler scheduler;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		/*
		 * Instantiates connection pool.
		 */
		try {
			ConnectionPool.getInstance().initPoolData();

		} catch (ConnectionPoolException e) {
			logger.error("Exception was thrown when trying to initialize connection pool", e);
			throw new RuntimeException(e);
		}
		/*
		 * Starts the scheduler that applies subscription fees to users'
		 * accounts balances on a daily basis.
		 */
		scheduler = new DailyFeeScheduler();
		scheduler.startTask();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		/*
		 * Disposes connection pool.
		 */
		try {
			ConnectionPool.getInstance().dispose();
		} catch (ConnectionPoolException e) {
			logger.error("Exception was thrown when trying to dispose connection pool", e);
		}
		/*
		 * Stops the scheduler that applies subscription fees to users' accounts
		 * balances on a daily basis.
		 */
		scheduler.stopTask();
	}

}
