package by.training.figure.builder;

import by.training.figure.entity.Point;

public class Validator {

	public static boolean validate(Point a, Point b, Point c) {
		//checks that points are not on the same line or two points do not coinside
		if (((a.getX() - c.getX()) * (b.getY() - c.getY()) != (b.getX() - c.getX())
		        * (a.getY() - c.getY())) || (!a.equals(b) && !b.equals(c) && !c.equals(a))) { 
			return true;
		} else {
			return false;
		}
	}

}
