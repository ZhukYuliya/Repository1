package by.training.figure.action;

import org.apache.log4j.Logger;

import by.training.figure.builder.TextFileTriangleBuilder;
import by.training.figure.entity.Triangle;
import by.training.figure.exception.InvalidTriangleDataException;
import by.training.figure.exception.NotTriangleException;
import by.training.figure.util.TriangleUtils;

public class TriangleAction {
	private final static Logger LOGGER = Logger.getLogger(TriangleAction.class);

	public static void main(String[] args) {
		TextFileTriangleBuilder builder = new TextFileTriangleBuilder();
		Triangle currentTriangle = null;
		try {
			currentTriangle = builder.getTriangle("triangle_data");
			boolean isRight = TriangleUtils.isRightangled(currentTriangle);
			System.out.println("Is the triangle right-angled? " + isRight);
			System.out.println("Perimeter:" + TriangleUtils.calculatePerimeter(currentTriangle));
			System.out.println("Square:" + TriangleUtils.calculateSquare(currentTriangle));
		} catch (InvalidTriangleDataException | NotTriangleException e) {
			LOGGER.error(e);
		}

	}

}
