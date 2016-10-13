package by.training.task2.factory;

import java.io.IOException;

import by.training.task2.creator.CargoTrainBuilder;
import by.training.task2.creator.PassengerTrainBuilder;
import by.training.task2.entity.Train;
import by.training.task2.exception.InvalidInputException;
import by.training.task2.exception.NoSuchTrainException;
import by.training.task2.reader.FileTrainReader;

public class TrainFactory {
	
	public static Train createTrain(TrainType type, String name, FileTrainReader reader) 
			throws InvalidInputException, NoSuchTrainException, NumberFormatException, IOException {
		Train train;
		
		switch (type) {
		case CARGO:
			train = new CargoTrainBuilder().buildCargoTrain(name, reader);
			break;
		case PASSENGER:
			train = new PassengerTrainBuilder().buildPassengerTrain(name, reader);
			break;
		default:
			throw new NoSuchTrainException("There's no such train");
		}
		train.setTrainType(type);
		return train;
	}
}
