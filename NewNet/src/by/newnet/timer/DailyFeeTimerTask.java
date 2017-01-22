package by.newnet.timer;

import java.util.TimerTask;

import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;

public class DailyFeeTimerTask extends TimerTask {

		@Override
		public void run() {
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				userService.applyDailyFee();
			} catch (ServiceException e) {
				// exception?message needed? message registration failed
				
				// log
			}
	}

}
