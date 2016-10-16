package by.training.figure.util;

import by.training.figure.entity.Point;
import by.training.figure.entity.Triangle;

public class TriangleUtils {
	private static final double PRECISION = 0.001;

	public static double calculateSquare(Triangle triangle) {
		double square;
		square = 0.5 * ((triangle.getA().getX() - triangle.getC().getX())
		        * (triangle.getB().getY() - triangle.getC().getY())
		        - (triangle.getB().getX() - triangle.getC().getX())
		                * (triangle.getA().getY() - triangle.getC().getY()));
		return square;
	}

	public static double calculatePerimeter(Triangle triangle) {
		double ab;
		double ac;
		double bc;
		ab = getLength(triangle.getA(), triangle.getB());
		ac = getLength(triangle.getA(), triangle.getC());
		bc = getLength(triangle.getB(), triangle.getB());
		double perimeter = ab + ac + bc;
		return perimeter;
	}

	public static double getLength(Point start, Point end) {
		double length = Math.sqrt(Math.pow((end.getX() - start.getX()), 2)
		        + Math.pow((end.getY() - start.getY()), 2));
		return length;

	}

	public static boolean isRightangled(Triangle triangle) {
		double ab;
		double ac;
		double bc;
		ab = getLength(triangle.getA(), triangle.getB());
		ac = getLength(triangle.getA(), triangle.getC());
		bc = getLength(triangle.getB(), triangle.getC());
		if (isEqualWithPrecision(ab * ab + ac * ac, bc * bc)
		        || isEqualWithPrecision(ab * ab + bc * bc, ac * ac)
		        || isEqualWithPrecision(ac * ac + bc * bc, ab * ab)) {

			return true;
		}
		return false;
	}

	public static boolean isEqualWithPrecision(double first, double second) {
		return Math.abs(first - second) < PRECISION;
	}
}
