package by.training.logisticbase.entity;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import by.training.logisticbase.exception.TruckException;

public class Warehouse {
	private final static Logger logger = Logger.getLogger(Truck.class);
	
	public static final int DEFAULT_OCCUPANCY = 1_000_000;
	public static final int TERMINALS_QUANTITY = 5;
	
	private AtomicInteger currentWarehouseOccupancy = new AtomicInteger(DEFAULT_OCCUPANCY);
	private TruckSemaphoreWithPriority semaphore;

	public Warehouse() {
		semaphore = new TruckSemaphoreWithPriority(TERMINALS_QUANTITY);
	}

	public int getCurrentWarehouseOccupancy() {
		return currentWarehouseOccupancy.get();
	}

	public void enterWarehouse() {
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			logger.error(e);
		}
	}

	public void leaveWarehouse() {
		semaphore.release();
	}

	public void unload(Truck truck) throws TruckException {
		currentWarehouseOccupancy.addAndGet(truck.getCargoWeight());
		truck.setCargoWeight(0);
	}

	public void load(Truck truck, int cargoWeightKg) throws TruckException {
		truck.setCargoWeight(cargoWeightKg);
		currentWarehouseOccupancy.addAndGet(-truck.getCargoWeight());
	}
}
