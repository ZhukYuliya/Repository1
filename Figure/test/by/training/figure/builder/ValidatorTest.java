package by.training.figure.builder;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import by.training.figure.exception.InvalidTriangleDataException;
import by.training.figure.exception.NotTriangleException;

public class ValidatorTest {
	@Test
	public void isTriangle() throws InvalidTriangleDataException, NotTriangleException {
		TextFileTriangleBuilder builder = new TextFileTriangleBuilder();
		builder.getTriangle("is_triangle_test_data");
		//would fail with exception if not a triangle
	}

	@Test()
	public void isNotTriangle() throws InvalidTriangleDataException {
		TextFileTriangleBuilder builder = new TextFileTriangleBuilder();
		try {
			builder.getTriangle("is_not_triangle_test_data");
			fail("Test should have thrown a NotTriangleException");
		} catch (NotTriangleException e) {
			Assert.assertEquals("It's a line, not a triangle", e.getMessage());
		}

	}

}
