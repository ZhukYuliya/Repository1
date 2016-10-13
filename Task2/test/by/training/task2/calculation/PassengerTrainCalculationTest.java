package by.training.task2.calculation;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import by.training.task2.entity.Train;
import by.training.task2.exception.InvalidInputException;
import by.training.task2.exception.NoSuchTrainException;
import by.training.task2.factory.TrainFactory;
import by.training.task2.factory.TrainType;
import by.training.task2.reader.FileTrainReader;

public class PassengerTrainCalculationTest {

	@Test
	public void calculatePassengersTest()
	        throws NumberFormatException, InvalidInputException, NoSuchTrainException, IOException {
		FileTrainReader fileTrainReader = new FileTrainReader("passenger_carriage_data_test.txt");
		Train currentTrain =
		        TrainFactory.createTrain(TrainType.PASSENGER, "no_name", fileTrainReader);
		int expected = 490;
		int actual = PassengerTrainCalculation.calculatePassengers(currentTrain);
		Assert.assertEquals("Passenger number is wrong", expected, actual);

	}
}
