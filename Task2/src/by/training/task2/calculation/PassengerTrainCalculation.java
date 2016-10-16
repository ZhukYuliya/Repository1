package by.training.task2.calculation;

import java.util.ArrayList;
import java.util.List;

import by.training.task2.entity.PassengerCarriage;
import by.training.task2.entity.Train;
import by.training.task2.factory.TrainType;

public class PassengerTrainCalculation {
	public static int calculatePassengers(Train passengerTrain) {
		if (passengerTrain.getTrainType() == TrainType.PASSENGER) {
			int passengerNumber = 0;
			for (int i = 0; i < passengerTrain.size(); i++) {
				passengerNumber += ((PassengerCarriage) passengerTrain.get(i)).getPassengerNumber();
			}
			return passengerNumber;
		} else {
			return 0;
		}
	}

	public static int calculateLuggage(Train passengerTrain) {
		if (passengerTrain.getTrainType() == TrainType.PASSENGER) {
			int luggageNumber = 0;
			for (int i = 0; i < passengerTrain.size(); i++) {
				luggageNumber += ((PassengerCarriage) passengerTrain.get(i)).getLuggageNumber();
			}
			return luggageNumber;
		} else {
			return 0;
		}
	}

	public static List<PassengerCarriage> filterByPassengersInRange(Train passengerTrain, int min, int max) {
		List<PassengerCarriage> carriagesInRange = new ArrayList<PassengerCarriage>();
		for (int i = 0; i < passengerTrain.size(); i++) {
			int passengerNumber = ((PassengerCarriage) passengerTrain.get(i)).getPassengerNumber();
			if (passengerNumber >= min && passengerNumber <= max) {
				carriagesInRange.add((PassengerCarriage) passengerTrain.get(i));
			}
		}
		return carriagesInRange;
	}
}
