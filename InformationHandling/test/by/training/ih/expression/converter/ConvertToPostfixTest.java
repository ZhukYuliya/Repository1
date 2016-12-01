package by.training.ih.expression.converter;

import org.junit.Assert;
import org.junit.Test;

import by.training.ih.expression.converter.ConvertToPostfix;

public class ConvertToPostfixTest {

	public static final String INFIX_EXPRESSION = " 3+4*2/(1-5)";
	public static final String POSTFIX_EXPRESSION = "3 4 2 * 1 5 - / +";

	public static final String UNARY_EXPRESSION = "-1 +(1++)+3 + 4 * 2 / (-1 - 5 )-(++8)-(++8.2)-(8.6++)-(8.6--)";
	public static final String BINARY = "0-1 +(1+1)+3 + 4 * 2 / (0-1 - 5 )-(8+1)-(8.2+1)-(8.6+1)-(8.6-1)";
	
	@Test
	public void infixToPostfix() {
		String expected = POSTFIX_EXPRESSION;
		String actual = ConvertToPostfix.infixToPostfix(INFIX_EXPRESSION);
		
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void convertUnaryOperatorsToBinary() {
		String expected = BINARY;
		String actual = ConvertToPostfix.convertUnaryOperatorsToBinary(UNARY_EXPRESSION);
	
		Assert.assertEquals(expected, actual);

	}
}
