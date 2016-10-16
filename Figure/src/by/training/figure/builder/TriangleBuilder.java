package by.training.figure.builder;

import by.training.figure.entity.Triangle;
import by.training.figure.exception.InvalidTriangleDataException;
import by.training.figure.exception.NotTriangleException;

public interface TriangleBuilder {

	Triangle getTriangle(String textFile) throws InvalidTriangleDataException, NotTriangleException;

}
