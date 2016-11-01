package by.training.logisticbase.state;

import org.apache.log4j.Logger;

import by.training.logisticbase.entity.Truck;

public class FinalState implements TruckState {
	private final static Logger logger = Logger.getLogger(FinalState.class);

	private static FinalState instance = new FinalState();

	private FinalState() {
	}

	public static FinalState getInstance() {
		return instance;
	}

	@Override
	public void goNext(Truck truck) {
		logger.info(truck.getOrderNumber()  + ", has perishable cargo: " 
				+ truck.hasPerishableCargo() + " reached final state");
	}
}
