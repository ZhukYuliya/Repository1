package by.newnet.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.newnet.dao.jdbc.pool.ConnectionPool;
import by.newnet.dao.jdbc.pool.ConnectionPoolException;
import by.newnet.timer.DailyFeeScheduler;

public class ServletInitListener implements ServletContextListener {

    public ServletInitListener() {
        
    }
// can a field be in listener
    DailyFeeScheduler scheduler;
    public void contextInitialized(ServletContextEvent arg0)  { 
    	 try {
			ConnectionPool.getInstance().initPoolData();
			 scheduler = new DailyFeeScheduler();
			scheduler.startTask();
		} catch (ConnectionPoolException  e) {
			//log
			throw new RuntimeException(e);
		}
  
        
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
    	 ConnectionPool.getInstance().dispose();
 		//close scheduler
    	 scheduler.stopTask();
    }
	
}
