package by.training.logisticbase.state;

import org.apache.log4j.Logger;

import by.training.logisticbase.entity.Truck;

public class InitialState implements TruckState {
	private final static Logger logger = Logger.getLogger(InitialState.class);
	private static InitialState instance = new InitialState();

	private InitialState() {
	}

	public static InitialState getInstance() {
		return instance;
	}

	@Override
	public void goNext(Truck truck) {
		logger.info(truck.getOrderNumber()  + ", has perishable cargo: " 
				+ truck.hasPerishableCargo() + " created");
		truck.setCurrentState(WaitState.getInstance());
	}

}
