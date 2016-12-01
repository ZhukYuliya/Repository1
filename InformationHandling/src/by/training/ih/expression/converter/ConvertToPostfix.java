package by.training.ih.expression.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertToPostfix {
	
	public static final Map<String, Integer> OPERATIONS;

	public static final String LEFT_BRACKET = "(";
	public static final String RIGHT_BRACKET = ")";
	public static final String INCREMENT = "++";
	public static final String DECREMENT = "--";
	public static final String MULTIPLY = "*";
	public static final String DIVIDE = "/";
	public static final String PLUS = "+";
	public static final String MINUS = "-";

	public static final Pattern MATH_EXPRESSION_ELEMENT_PATTERN =
	        Pattern.compile("(\\d+(\\.\\d+)?)|[+\\-\\/*()]");

	public static final String SPACE = " ";

	private static final Pattern MINUS_AFTER_LEFT_BRACKET_PATTERN = Pattern.compile("\\(-");
	private static final Pattern DECREMENT_INCREMENT_PATTERN =
	        Pattern.compile("-{2}(\\d+(\\.\\d+)?)|(\\d+(\\.\\d+)?)-{2}"
	                + "|\\+{2}(\\d+(\\.\\d+)?)|(\\d+(\\.\\d+)?)\\+{2}");

	static {
		OPERATIONS = new HashMap<String, Integer>();
		OPERATIONS.put(MULTIPLY, 1);
		OPERATIONS.put(DIVIDE, 1);
		OPERATIONS.put(PLUS, 0);
		OPERATIONS.put(MINUS, 0);
	}

	public static String infixToPostfix(String infix) {
		StringBuilder out = new StringBuilder();
		Stack<String> stack = new Stack<>();

		infix = infix.trim();
		infix = convertUnaryOperatorsToBinary(infix);
		Matcher expressionElementMatcher = MATH_EXPRESSION_ELEMENT_PATTERN.matcher(infix);
		while (expressionElementMatcher.find()) {
			String token = expressionElementMatcher.group();
			if (OPERATIONS.containsKey(token)) {
				if (stack.isEmpty()) {
					stack.push(token);
				} else {
					while (!stack.isEmpty()) {
						if (stack.peek().equals(LEFT_BRACKET)){
							break;
						}
						int stackTopPriority = OPERATIONS.get(stack.peek());

						int tokenPriority = OPERATIONS.get(token);
						if (stackTopPriority >= tokenPriority) {
							out.append(stack.pop()).append(SPACE);
						} else {
							break;
						}
					}
					stack.push(token);
				}
			} else if (token.equals(LEFT_BRACKET)) {
				stack.push(LEFT_BRACKET);
			} else if (token.equals(RIGHT_BRACKET)) {
				while (!stack.peek().equals(LEFT_BRACKET)) {
					out.append(stack.pop()).append(SPACE);
				}
				stack.pop();
			} else {
				out.append(token).append(SPACE);
			}
		}
		while (!stack.isEmpty()) {
			out.append(stack.pop()).append(SPACE);
		}
		//trims to get rid of the last space
		return out.toString().trim();
	}

	public static String convertUnaryOperatorsToBinary(String infix) {
		String correctInfix = null;
		Matcher decrementIncrementMatcher = DECREMENT_INCREMENT_PATTERN.matcher(infix);
		StringBuffer sb = new StringBuffer();
		while (decrementIncrementMatcher.find()) {
			String number;
			if (decrementIncrementMatcher.group(1) != null) {
				number = decrementIncrementMatcher.group(1);
				decrementIncrementMatcher.appendReplacement(sb, number + "-1");
			} else if (decrementIncrementMatcher.group(3) != null) {
				number = decrementIncrementMatcher.group(3);
				decrementIncrementMatcher.appendReplacement(sb, number + "-1");
			} else if (decrementIncrementMatcher.group(5) != null) {
				number = decrementIncrementMatcher.group(5);
				decrementIncrementMatcher.appendReplacement(sb, number + "+1");
			} else if (decrementIncrementMatcher.group(7) != null) {
				number = decrementIncrementMatcher.group(7);
				decrementIncrementMatcher.appendReplacement(sb, number + "+1");
			}
		}
		decrementIncrementMatcher.appendTail(sb);
		correctInfix = sb.toString();
		
		if (correctInfix.startsWith("-")) {
			correctInfix = 0 + correctInfix;
		}
		Matcher minusMatcher = MINUS_AFTER_LEFT_BRACKET_PATTERN.matcher(correctInfix);
		correctInfix = minusMatcher.replaceAll("(0-");
		return correctInfix;

	}
}
