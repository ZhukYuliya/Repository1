package by.training.ih.expression.interpreter;

import java.util.ArrayDeque;

public class Context {
	private ArrayDeque<Double> contextValues = new ArrayDeque<>();

	public double popValue() {
		return contextValues.pop();
	}

	public void pushValue(double number) {
		this.contextValues.push(number);
	}

	public boolean isEmpty() {
		return contextValues.isEmpty();
	}

}
