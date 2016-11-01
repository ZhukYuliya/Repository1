package by.training.logisticbase.state;

import by.training.logisticbase.entity.Truck;

public class WaitState implements TruckState {
	private static WaitState instance = new WaitState();

	private WaitState() {
	}

	public static WaitState getInstance() {
		return instance;
	}

	@Override
	public void goNext(Truck truck) {
		truck.getDestination().enterWarehouse();
		truck.setCurrentState(ProcessState.getInstance());
	}

}
