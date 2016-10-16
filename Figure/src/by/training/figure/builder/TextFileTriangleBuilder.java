package by.training.figure.builder;

import by.training.figure.entity.Point;
import by.training.figure.entity.Triangle;
import by.training.figure.exception.InvalidTriangleDataException;
import by.training.figure.exception.NotTriangleException;
import by.training.figure.reader.TriangleFileReader;

public class TextFileTriangleBuilder implements TriangleBuilder {

	@Override
	public Triangle getTriangle(String textFile)
	        throws InvalidTriangleDataException, NotTriangleException {
		TriangleFileReader reader = new TriangleFileReader(textFile);
		String[] trianglePoints = reader.read();
		Point a = null;
		Point b = null;
		Point c = null;
		
		try {
			a = parsePoint(trianglePoints[0]);
			b = parsePoint(trianglePoints[1]);
			c = parsePoint(trianglePoints[2]);
		} catch (NumberFormatException e) {
			throw new InvalidTriangleDataException("Wrong input data");
		}
		Triangle triangle;
		boolean isTriangle = Validator.validate(a, b, c);
		if (isTriangle) {
			triangle = new Triangle(a, b, c);
		} else {
			throw new NotTriangleException("It's a line, not a triangle");
		}
		return triangle;
	}
	
	private Point parsePoint(String stringCoordinates) {
		String[] stringCoodinatesArray = stringCoordinates.split(",");
		Point point = new Point();
		point.setX(Double.valueOf(stringCoodinatesArray[0]));
		point.setY(Double.valueOf(stringCoodinatesArray[1]));
		
		return point;
		
	}
}
