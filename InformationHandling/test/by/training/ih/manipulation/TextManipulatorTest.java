package by.training.ih.manipulation;

import org.junit.Assert;
import org.junit.Test;

import by.training.ih.composite.CompositeText;
import by.training.ih.manupulation.TextManipulator;
import by.training.ih.parser.TextParser;

public class TextManipulatorTest {
	public static final String TEST_REPETITION = "Cat cat. Dog and dog??? Bird birds. Big cat.";
	public static final String TEST_ASCENDANT =
	        "One, two, three, four. Cat. Dog  or dog??? Bird birds. Big cat.";

	@Test
	public void findMaxRepetitionTest() {
		String expected = "Cat cat. Dog and dog???";
		CompositeText text = TextParser.INSTANCE.parse(TEST_REPETITION);
		String actual = TextManipulator.findMaxRepetition(text);
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void removeLexemesTest() {
		String expected = "Cat Dog and dog??? Bird birds. Big";
		CompositeText text = TextParser.INSTANCE.parse(TEST_REPETITION);
		String actual = TextManipulator.removeLexemes(text, 4, 'c').toString().trim();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void sentencesByLexemesQuantityTest() {
		String expected = "Cat. Bird birds. Big cat. Dog or dog??? One, two, three, four.";
		CompositeText text = TextParser.INSTANCE.parse(TEST_ASCENDANT);
		String actual = TextManipulator.sentencesByLexemesQuantity(text).toString().trim();
		Assert.assertEquals(expected, actual);
	}
}
