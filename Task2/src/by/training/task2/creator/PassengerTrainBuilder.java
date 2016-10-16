package by.training.task2.creator;

import java.io.IOException;

import by.training.task2.entity.ComfortLevel;
import by.training.task2.entity.PassengerCarriage;
import by.training.task2.entity.Train;
import by.training.task2.exception.InvalidInputException;
import by.training.task2.reader.FileTrainReader;

public class PassengerTrainBuilder {

	public Train buildPassengerTrain(String name, FileTrainReader reader)
	        throws IOException, InvalidInputException {
		Train passengerTrain = new Train(name);

		String line;
		while ((line = reader.readNextLine()) != null) {
			String[] carriageData = line.split(",");
			ComfortLevel comfortLevel;
			int passengerNumber;
			int luggageNumber;
			String parkName = carriageData[0];
			try {
				comfortLevel = ComfortLevel.valueOf(carriageData[1].toUpperCase());
				passengerNumber = Integer.valueOf(carriageData[2]);
				luggageNumber = Integer.valueOf(carriageData[3]);
			} catch ( IllegalArgumentException e) {
				throw new InvalidInputException("Wrong input data");
			}

			PassengerCarriage currentCarriage =
			        new PassengerCarriage(parkName, comfortLevel, passengerNumber, luggageNumber);
			passengerTrain.add(currentCarriage);
		}
		return passengerTrain;
	}
}
