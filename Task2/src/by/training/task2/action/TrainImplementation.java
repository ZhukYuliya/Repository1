package by.training.task2.action;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import by.training.task2.calculation.PassengerTrainCalculation;
import by.training.task2.entity.PassengerCarriage;
import by.training.task2.entity.Train;
import by.training.task2.entity.comparator.PassengerCarriageComparator;
import by.training.task2.exception.InvalidInputException;
import by.training.task2.exception.NoSuchTrainException;
import by.training.task2.factory.TrainFactory;
import by.training.task2.factory.TrainType;
import by.training.task2.reader.FileTrainReader;

public class TrainImplementation {
	private final static Logger logger = Logger.getLogger(TrainImplementation.class);

	public static void main(String[] args) {
		Train expressTrain = null;
		try {
			expressTrain = TrainFactory.createTrain(TrainType.PASSENGER, "MoscowExpress",
			        new FileTrainReader("passenger_carriage_data.txt"));
		} catch (NumberFormatException | InvalidInputException | NoSuchTrainException
		        | IOException e) {
			logger.error(e);
			
		}

		int luggageNumber = PassengerTrainCalculation.calculateLuggage(expressTrain);
		int passengerNumber = PassengerTrainCalculation.calculatePassengers(expressTrain);

		expressTrain.sort(new PassengerCarriageComparator());

		List<PassengerCarriage> between50and100 =
		        PassengerTrainCalculation.inPassengerRange(expressTrain, 50, 100);

		System.out.println(luggageNumber);
		System.out.println(passengerNumber);
		System.out.println(between50and100.get(0));

	}

}
