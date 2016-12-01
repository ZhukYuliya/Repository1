package by.training.ih.expression.interpreter;

import org.junit.Assert;
import org.junit.Test;

import by.training.ih.expression.interpreter.Client;

public class ClientTest {
	
    public static final String TEST_EXPRESSION = "15 2 3 2 1 - * + /";

    @Test
    public void calculateTest(){
        Double expected = 3.0;
        Client interpreter = new Client(TEST_EXPRESSION);
        Double actual = interpreter.calculate();
        Assert.assertEquals(expected , actual);
    }
    
}


