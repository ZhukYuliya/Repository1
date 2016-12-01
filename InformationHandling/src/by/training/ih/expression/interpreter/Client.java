package by.training.ih.expression.interpreter;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {

	public static final String EXPRESSION_DELIMETER = "\\p{Blank}+";

	private ArrayList<AbstractMathExpression> listExpression;

	public Client(String expression) {
		listExpression = new ArrayList<>();
		parse(expression);
	}

	private void parse(String expression) { 
		for (String lexeme : expression.trim().split(EXPRESSION_DELIMETER)) {
			switch (lexeme) {
			case "+":
				listExpression.add(new TerminalExpressionPlus());
				break;
			case "-":
				listExpression.add(new TerminalExpressionMinus());
				break;
			case "*":
				listExpression.add(new TerminalExpressionMultiply());
				break;
			case "/":
				listExpression.add(new TerminalExpressionDivide());
				break;
			default:
				Scanner scan = new Scanner(lexeme);
				if (scan.hasNextDouble()) {
					listExpression.add(new NonTerminalExpressionNumber(scan.nextDouble()));
				}
			}
		}
	}
	public Double calculate() {
        Context context = new Context();
        for (AbstractMathExpression expression : listExpression) {
        	expression.interpret(context);
        }
        return context.popValue();
    }
}
