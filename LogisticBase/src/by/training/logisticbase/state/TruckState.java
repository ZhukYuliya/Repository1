package by.training.logisticbase.state;

import by.training.logisticbase.entity.Truck;
import by.training.logisticbase.exception.TruckException;

public interface TruckState {
	
	public void goNext(Truck truck) throws TruckException;
	

}
