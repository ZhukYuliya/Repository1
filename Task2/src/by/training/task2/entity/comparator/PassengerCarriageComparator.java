package by.training.task2.entity.comparator;

import java.util.Comparator;

import by.training.task2.entity.Carriage;
import by.training.task2.entity.PassengerCarriage;

public class PassengerCarriageComparator implements Comparator<Carriage> {
	public int compare(Carriage car1, Carriage car2) {
		return ((PassengerCarriage)car1).getComfortLevel().getLevelNumber() - 
				((PassengerCarriage)car2).getComfortLevel().getLevelNumber();
		
	}

}
