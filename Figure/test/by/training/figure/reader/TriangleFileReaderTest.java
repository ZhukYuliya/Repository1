package by.training.figure.reader;

import org.junit.Assert;
import org.junit.Test;

import by.training.figure.exception.InvalidTriangleDataException;

public class TriangleFileReaderTest {

	@Test
	public void readTest() throws InvalidTriangleDataException {
		TriangleFileReader reader = new TriangleFileReader("triangle_file_reader_test_data");
		String[] data = reader.read();
		Assert.assertNotNull(data);
		int expected = 3;
		int actual = data.length;
		Assert.assertEquals(expected, actual);
	}

	@Test(expected = InvalidTriangleDataException.class)
	public void readWrongFileNameTest() throws InvalidTriangleDataException {
		TriangleFileReader reader = new TriangleFileReader("wrong_triangle_file_reader_test_data");
		reader.read();
	}

}
