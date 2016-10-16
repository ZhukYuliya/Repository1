package by.training.figure.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import by.training.figure.exception.InvalidTriangleDataException;

public class TriangleFileReader implements TriangleReader {
	private String trianglefilename;

	public TriangleFileReader(String filename) {
		trianglefilename = filename;
	}

	@Override
	public String[] read() throws InvalidTriangleDataException {
		Scanner in = null;
		String[] trianglePoints = new String[3];
		try {
			in = new Scanner(new File(trianglefilename));
			for (int i = 0; i < trianglePoints.length; i++) {
				trianglePoints[i] = in.nextLine();
			}
		} catch (IllegalStateException | NoSuchElementException | FileNotFoundException e) {
			throw new InvalidTriangleDataException("Wrong input data " + e.toString());
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return trianglePoints;
	}
}
