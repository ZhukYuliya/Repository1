package by.training.ih.expression.interpreter;

public class TerminalExpressionPlus extends AbstractMathExpression {

	@Override
	public void interpret(Context c) {
		c.pushValue(c.popValue() + c.popValue());

	}

}
