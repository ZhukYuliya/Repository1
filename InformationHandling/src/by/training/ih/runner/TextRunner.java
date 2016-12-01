package by.training.ih.runner;

import org.apache.log4j.Logger;

import by.training.ih.composite.CompositeText;
import by.training.ih.manupulation.TextManipulator;
import by.training.ih.parser.TextParser;
import by.training.ih.reader.TxtReader;
import by.training.ih.reader.TxtReaderException;

public class TextRunner {

	public static final String FILENAME = "resources/text.txt";
	public static final char SYMBOL = 'i';
	public static final int LEXEME_SIZE = 2;

	private static final Logger LOGGER = Logger.getLogger(TextRunner.class);

	public static void main(String[] args) {

		String text = null;
		try {
			text = TxtReader.readTxt(FILENAME);
		} catch (TxtReaderException e) {
			LOGGER.error(e.getMessage(), e);
		}
		TextParser textParser = TextParser.INSTANCE;
		CompositeText compositeText = textParser.parse(text);

		System.out.println(compositeText);

		System.out.println(TextManipulator.findMaxRepetition(compositeText));
		System.out.println(TextManipulator.removeLexemes(compositeText, LEXEME_SIZE, SYMBOL));
		System.out.println(TextManipulator.sentencesByLexemesQuantity(compositeText));

	}
}
