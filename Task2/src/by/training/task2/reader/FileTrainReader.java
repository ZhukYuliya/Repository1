package by.training.task2.reader;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;

public class FileTrainReader implements Closeable {

	private BufferedReader reader;

	public FileTrainReader(String fileName) throws IOException {
		reader = new BufferedReader(new FileReader(fileName));
	}

	public String readNextLine() throws IOException {
		return reader.readLine();
	}

	@Override
	public void close() throws IOException {
		reader.close();

	}

}
