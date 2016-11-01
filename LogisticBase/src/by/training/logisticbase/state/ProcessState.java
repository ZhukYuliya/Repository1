package by.training.logisticbase.state;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import by.training.logisticbase.entity.Truck;
import by.training.logisticbase.exception.TruckException;

public class ProcessState implements TruckState {
	private final static Logger logger = Logger.getLogger(ProcessState.class);

	private static ProcessState instance = new ProcessState();
	Random random = new Random();

	private ProcessState() {
	}

	public static ProcessState getInstance() {
		return instance;
	}

	@Override
	public void goNext(Truck truck) throws TruckException {
		try {
			TimeUnit.MILLISECONDS.sleep(truck.getCargoWeight() / 10);
		} catch (InterruptedException e) {
			logger.error(e);
		}
		if (truck.getCargoWeight() == 0) {
			truck.getDestination().unload(truck);
		} else {
			truck.getDestination().load(truck, random.nextInt(Truck.MAX_CARGO_WEIGHT_KG));
		}
		truck.getDestination().leaveWarehouse();
		truck.setCurrentState(FinalState.getInstance());

	}

}
