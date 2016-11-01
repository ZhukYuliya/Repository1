package by.training.logisticbase;

import java.util.Random;

import org.apache.log4j.Logger;

import by.training.logisticbase.entity.Truck;
import by.training.logisticbase.entity.Warehouse;
import by.training.logisticbase.exception.TruckException;

public class LogisticBaseWork {
	private final static Logger logger = Logger.getLogger(LogisticBaseWork.class);
	public static void main (String [] args){
		
		Warehouse warehouse = new Warehouse();
		Random random = new Random();
	
		for (int i = 1; i < 15; i++) {
			Truck truck;
			  try {
				 truck = new Truck(random.nextInt(Truck.MAX_CARGO_WEIGHT_KG), random.nextBoolean(), i, warehouse);
				 truck.start();
			} catch (TruckException e) {
				logger.error(e);
			}     
		}	
	}
}
