package by.training.figure.util;

import org.junit.Assert;
import org.junit.Test;

import by.training.figure.builder.TextFileTriangleBuilder;
import by.training.figure.entity.Triangle;
import by.training.figure.exception.InvalidTriangleDataException;
import by.training.figure.exception.NotTriangleException;

public class TriangleUtilsTest {
	@Test
	public void calculateSquareTest() throws InvalidTriangleDataException, NotTriangleException {
		TextFileTriangleBuilder builder = new TextFileTriangleBuilder();
		Triangle triangle = builder.getTriangle("square_perimeter_test_data");
		double expected = 0.5;
		double actual = TriangleUtils.calculateSquare(triangle);
		Assert.assertEquals(expected, actual, 0.01);
	}

	@Test
	public void calculatePerimeterTest() throws InvalidTriangleDataException, NotTriangleException {
		TextFileTriangleBuilder builder = new TextFileTriangleBuilder();
		Triangle triangle = builder.getTriangle("square_perimeter_test_data");
		double expected = 4.495;
		double actual = TriangleUtils.calculatePerimeter(triangle);
		Assert.assertEquals(expected, actual, 0.01);
	}

	@Test
	public void isNotRightangledTest() throws InvalidTriangleDataException, NotTriangleException {
		TextFileTriangleBuilder builder = new TextFileTriangleBuilder();
		Triangle triangle = builder.getTriangle("is_not_rightangled_test_data");
		boolean result = TriangleUtils.isRightangled(triangle);
		Assert.assertFalse(result);
	}
	@Test
	public void isRightangledTest() throws InvalidTriangleDataException, NotTriangleException {
		TextFileTriangleBuilder builder = new TextFileTriangleBuilder();
		Triangle triangle = builder.getTriangle("is_rightangled_test_data");
		boolean result = TriangleUtils.isRightangled(triangle);
		Assert.assertTrue(result);
	}
}
