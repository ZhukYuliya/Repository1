package timer;

import java.util.TimerTask;

import org.apache.log4j.Logger;

import service.ServiceFactory;
import service.UserService;
import service.exception.ServiceException;
import tag.CopyrightTag;

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
