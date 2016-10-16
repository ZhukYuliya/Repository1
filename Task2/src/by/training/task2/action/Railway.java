package by.training.task2.action;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import by.training.task2.calculation.PassengerTrainCalculation;
import by.training.task2.entity.PassengerCarriage;
import by.training.task2.entity.Train;
import by.training.task2.entity.comparator.PassengerCarriageComparator;
import by.training.task2.exception.InvalidInputException;
import by.training.task2.exception.UnknownTrainTypeException;
import by.training.task2.factory.TrainFactory;
import by.training.task2.factory.TrainType;
import by.training.task2.reader.FileTrainReader;

public class Railway {
	private final static Logger logger = Logger.getLogger(Railway.class);

	public static void main(String[] args) {
		Train expressTrain = null;
		FileTrainReader reader = null;;
		try {
			reader = new FileTrainReader("passenger_carriage_data.txt");
			expressTrain = TrainFactory.createTrain(TrainType.PASSENGER, "MoscowExpress",
			        reader);
		} catch (NumberFormatException | InvalidInputException | UnknownTrainTypeException
		        | IOException e) {
			logger.error(e);

		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				logger.error(e);
			}
		}

		int luggageNumber = PassengerTrainCalculation.calculateLuggage(expressTrain);
		int passengerNumber = PassengerTrainCalculation.calculatePassengers(expressTrain);

		expressTrain.sort(new PassengerCarriageComparator());

		List<PassengerCarriage> between50and100 =
		        PassengerTrainCalculation.filterByPassengersInRange(expressTrain, 50, 100);

		System.out.println(luggageNumber);
		System.out.println(passengerNumber);
		System.out.println(between50and100.get(0));

	}

}
