package by.training.ih.parser;

import org.junit.Assert;
import org.junit.Test;

import by.training.ih.composite.CompositeText;
import by.training.ih.composite.TextComponent;

public class TextParserTest {

  
	public static final String TEST_TEXT = "This is a 2.6 test text! Second sentence. Third? Next!";
	
	@Test
	public void parse(){
		int expectedParagraphsNumber = 1;
		int expectedSentencesNumber = 4;
		int expectedLexemesNumber = 10;

        TextParser parser = TextParser.INSTANCE;
        CompositeText text = parser.parse(TEST_TEXT);
        int actualParagraphsNumber = text.getComponents().size();
        int actualSentencesNumber = 0;
        int actualLexemesNumber = 0;

        for(TextComponent paragraph : text.getComponents()){
        	actualSentencesNumber += paragraph.getComponents().size();
        	for (TextComponent sentence : paragraph.getComponents()){
        		actualLexemesNumber += sentence.getComponents().size();
        	}
        }
        Assert.assertEquals(expectedParagraphsNumber, actualParagraphsNumber);
        Assert.assertEquals(expectedSentencesNumber, actualSentencesNumber);
        Assert.assertEquals(expectedLexemesNumber, actualLexemesNumber);

    }

}
