package by.training.task2.creator;

import java.io.IOException;

import by.training.task2.entity.CargoCarriage;
import by.training.task2.entity.Train;
import by.training.task2.reader.FileTrainReader;

public class CargoTrainBuilder {

	public Train buildCargoTrain(String name, FileTrainReader reader)
	        throws NumberFormatException, IOException {
		Train cargoTrain = new Train(name);
		String line;
		while ((line = reader.readNextLine()) != null) {
			String[] carriageData = line.split(",");

			String parkName = carriageData[0];
			int cargoCapacity = Integer.valueOf(carriageData[1]);
			CargoCarriage currentCarriage = new CargoCarriage(parkName, cargoCapacity);
			cargoTrain.add(currentCarriage);
		}

		return cargoTrain;
	}
}
