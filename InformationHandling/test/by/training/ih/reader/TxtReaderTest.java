package by.training.ih.reader;

import org.junit.Assert;
import org.junit.Test;

public class TxtReaderTest {
	
    public static final String TEST_FILE="resources/test_text.txt";
	public static final String TEST_TEXT = "This is a test text!";
	
	@Test
    public void readTxt() throws TxtReaderException{
        String expected=TEST_TEXT;
        String actual= TxtReader.readTxt(TEST_FILE).trim();
        Assert.assertEquals(expected, actual);

    }
	
	
}
