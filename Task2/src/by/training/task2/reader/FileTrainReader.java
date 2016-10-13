package by.training.task2.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileTrainReader {
	
	private BufferedReader reader;
	public FileTrainReader(String fileName)throws IOException {
		reader = new BufferedReader(new FileReader(fileName));
	}
	public String readNextLine () throws IOException{
		return reader.readLine();
	}
	
	public void closeReader() throws IOException{
		reader.close();
	}
}
		