package by.training.figure.reader;

import by.training.figure.exception.InvalidTriangleDataException;

public interface TriangleReader {
	
	public String [] read () throws InvalidTriangleDataException;

}
