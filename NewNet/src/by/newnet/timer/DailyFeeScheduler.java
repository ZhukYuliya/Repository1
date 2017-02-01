package by.newnet.timer;

import java.util.Date;
import java.util.Timer;

/**
 * The Class DailyFeeScheduler.
 */
public class DailyFeeScheduler {
	private Timer timer;

	/**
	 * Instantiates a new daily fee scheduler.
	 */
	public DailyFeeScheduler() {
	}

	/**
	 * Start task.
	 */
	public void startTask() {
		DailyFeeTimerTask task = new DailyFeeTimerTask();
		timer = new Timer();
		/**
		 * Schedules the timer to execute task once a day starting from 02/01/2017 17:00
		 */
		Date startDate = new Date(1485957589235L);
		timer.scheduleAtFixedRate(task, startDate, 86400000L);
	}

	/**
	 * Stop task.
	 */
	public void stopTask() {
		timer.cancel();
	}

}
