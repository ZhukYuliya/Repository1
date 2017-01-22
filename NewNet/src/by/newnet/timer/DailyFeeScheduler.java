package by.newnet.timer;

import java.util.Timer;

public class DailyFeeScheduler {
	private Timer timer;

	public DailyFeeScheduler() {
	}

	public void startTask() {
		DailyFeeTimerTask task = new DailyFeeTimerTask();
		timer = new Timer();
		timer.scheduleAtFixedRate(task, 1L, 3600L);
	}

	public void stopTask() {
		timer.cancel();
	}

}
