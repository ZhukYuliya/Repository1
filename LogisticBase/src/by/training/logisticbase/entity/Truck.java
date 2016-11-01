package by.training.logisticbase.entity;

import org.apache.log4j.Logger;

import by.training.logisticbase.exception.TruckException;
import by.training.logisticbase.state.InitialState;
import by.training.logisticbase.state.FinalState;
import by.training.logisticbase.state.TruckState;

public class Truck extends Thread {
	private final static Logger logger = Logger.getLogger(Truck.class);

	public static final int MAX_CARGO_WEIGHT_KG = 22000;
	
	private int cargoWeightKg;
	private boolean perishableCargo;
	private int orderNumber;
	private TruckState currentState;
	private Warehouse destination;
	
	public Truck(){}
	
	public Truck(int cargoWeightKg, boolean perishableCargo, int orderNumber, Warehouse destination)
	        throws TruckException {
		if (0 <= cargoWeightKg && cargoWeightKg <= MAX_CARGO_WEIGHT_KG) {
			this.cargoWeightKg = cargoWeightKg;
		} else {
			throw new TruckException("Try loading a valid amount of goods.");
		}
		this.perishableCargo = perishableCargo;
		this.orderNumber = orderNumber;
		this.currentState = InitialState.getInstance();
		this.destination = destination;
	}

	public Warehouse getDestination() {
		return destination;
	}

	public void run() {
		while (!(currentState instanceof FinalState)) {
			try {
				currentState.goNext(this);
			} catch (TruckException e) {
				logger.error(e);
			}
		}
	}

	public int getCargoWeight() {
		return cargoWeightKg;
	}

	public void setCargoWeight(int cargoWeightKg) throws TruckException {
		if (cargoWeightKg <= MAX_CARGO_WEIGHT_KG) {
			this.cargoWeightKg = cargoWeightKg;
		} else {
			throw new TruckException("Try loading a valid amount of goods.");
		}
	}

	public boolean hasPerishableCargo() {
		return perishableCargo;
	}

	public void setPerishableCargo(boolean perishableCargo) {
		this.perishableCargo = perishableCargo;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public TruckState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(TruckState currentState) {
		this.currentState = currentState;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + orderNumber;
		return result;
	}
	
	// compares trucks only by order number because they are always unique unlike other fields
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Truck other = (Truck) obj;
		if (orderNumber != other.orderNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Truck [perishableCargo=" + perishableCargo + ", orderNumber=" + orderNumber + "]";
	}

}
