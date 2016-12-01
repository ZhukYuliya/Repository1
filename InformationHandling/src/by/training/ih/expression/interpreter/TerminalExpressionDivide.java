package by.training.ih.expression.interpreter;

public class TerminalExpressionDivide extends AbstractMathExpression {

	@Override
	public void interpret(Context c) {
		c.pushValue(1/c.popValue() * c.popValue());

	}

}
