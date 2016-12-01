package by.training.ih.expression.interpreter;

public class NonTerminalExpressionNumber extends AbstractMathExpression {

	private double number;

	public NonTerminalExpressionNumber(double d) {
		this.number = d;
	}

	@Override
	public void interpret(Context c) {
		c.pushValue(number);
	}

}
