package by.newnet.timer;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import by.newnet.service.ServiceFactory;
import by.newnet.service.UserService;
import by.newnet.service.exception.ServiceException;
import by.newnet.tag.CopyrightTag;

/**
 * The Class DailyFeeTimerTask.
 */
public class DailyFeeTimerTask extends TimerTask {
	
	private static final Logger logger = Logger.getLogger(CopyrightTag.class);

		@Override
		public void run() {
			UserService userService = ServiceFactory.getInstance().getUserService();
			try {
				userService.applyDailyFee();
			} catch (ServiceException e) {
				logger.error("Exception was thrown when trying to apply daily fee", e);
			}
	}

}
