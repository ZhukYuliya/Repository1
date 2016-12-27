package by.newnet.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.newnet.dao.jdbc.pool.ConnectionPool;
import by.newnet.dao.jdbc.pool.ConnectionPoolException;

public class ServletInitListener implements ServletContextListener {

    public ServletInitListener() {
        
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	 ConnectionPool.getInstance().dispose();
 		
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
    	 try {
			ConnectionPool.getInstance().initPoolData();
		} catch (ConnectionPoolException e) {
			new RuntimeException(e);
		}
        
    }
	
}
