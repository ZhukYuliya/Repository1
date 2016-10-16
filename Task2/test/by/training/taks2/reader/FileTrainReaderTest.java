package by.training.taks2.reader;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import by.training.task2.entity.Train;
import by.training.task2.exception.InvalidInputException;
import by.training.task2.exception.UnknownTrainTypeException;
import by.training.task2.factory.TrainFactory;
import by.training.task2.factory.TrainType;
import by.training.task2.reader.FileTrainReader;

public class FileTrainReaderTest {
	@Test
	public void trainReadingTest()
	        throws IOException, NumberFormatException, InvalidInputException, UnknownTrainTypeException {
		FileTrainReader fileTrainReader = new FileTrainReader("passenger_carriage_data_test.txt");
		Train currentTrain =
		        TrainFactory.createTrain(TrainType.PASSENGER, "no_name", fileTrainReader);
		Assert.assertEquals(4, currentTrain.size());
	}

	@Test(expected = IOException.class)
	public void invalideFileNameTest() throws IOException {
		FileTrainReader fileTrainReader = new FileTrainReader("non_existing_file.txt");
		fileTrainReader.readNextLine();
	}

	@Test(expected = InvalidInputException.class)
	public void invalidInputTest()
	        throws IOException, NumberFormatException, InvalidInputException, UnknownTrainTypeException {
		FileTrainReader fileTrainReader = new FileTrainReader("invalid_input_data_test.txt");
		@SuppressWarnings("unused")
		Train currentTrain =
		        TrainFactory.createTrain(TrainType.PASSENGER, "no_name", fileTrainReader);
	}

}
