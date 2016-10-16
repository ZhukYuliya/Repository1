package by.training.task2.creator;

import java.io.IOException;

import by.training.task2.entity.CargoCarriage;
import by.training.task2.entity.Train;
import by.training.task2.exception.InvalidInputException;
import by.training.task2.reader.FileTrainReader;

public class CargoTrainBuilder {

	public Train buildCargoTrain(String name, FileTrainReader reader)
	        throws IOException, InvalidInputException {
		Train cargoTrain = new Train(name);
		String line;
		while ((line = reader.readNextLine()) != null) {
			String[] carriageData = line.split(",");

			String parkName = carriageData[0];
			int cargoCapacity;
			try {
				cargoCapacity = Integer.valueOf(carriageData[1]);
			} catch (NumberFormatException e) {
				throw new InvalidInputException("Wrong input data");
			}
			CargoCarriage currentCarriage = new CargoCarriage(parkName, cargoCapacity);
			cargoTrain.add(currentCarriage);
		}

		return cargoTrain;
	}
}
