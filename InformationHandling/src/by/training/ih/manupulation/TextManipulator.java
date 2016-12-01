package by.training.ih.manupulation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import by.training.ih.composite.CompositeText;
import by.training.ih.composite.TextComponent;

public class TextManipulator {

	private final static Logger logger = Logger.getLogger(TextManipulator.class);

	private static final String PUNCTUATION = "\\p{Punct}+";

	// finds all the sentences with duplicating words inside this sentence
	public static String findMaxRepetition(CompositeText compositeText) {
		StringBuilder sentencesWithRepeatingWords = new StringBuilder();
		for (TextComponent paragraph : compositeText.getComponents()) {
			for (TextComponent sentence : paragraph.getComponents()) {
				// String - lexeme, Integer - number of occurences in the
				// sentence
				Map<String, Integer> occurrences = new HashMap<String, Integer>();
				for (TextComponent lexeme : sentence.getComponents()) {
					String stringLexeme = lexeme.toString();
					stringLexeme = stringLexeme.replaceAll(PUNCTUATION, "").toLowerCase();
					Integer oldCount = occurrences.get(stringLexeme);
					if (oldCount == null) {
						oldCount = 0;
					}
					occurrences.put(stringLexeme, oldCount + 1);
				}
				for (Integer i : occurrences.values()) {
					if (i >= 2) {
						sentencesWithRepeatingWords.append(sentence.toString());
					}
				}
			}
		}
		logger.info("The sentences with duplicating words are: " + sentencesWithRepeatingWords);
		return sentencesWithRepeatingWords.toString().trim();
	}

	// removes all the lexems of given length and starting from given letter
	public static CompositeText removeLexemes(CompositeText compositeText, int length,
	        char symbol) {
		CompositeText newCompositeText = new CompositeText(compositeText);
		for (TextComponent paragraph : newCompositeText.getComponents()) {
			for (TextComponent sentence : paragraph.getComponents()) {
				for (int i = 0; i < sentence.getComponents().size(); i++) {
					TextComponent lexeme = sentence.getComponents().get(i);
					if (lexeme.toString().toLowerCase().charAt(0) == symbol
					        && lexeme.toString().trim().length() == length) {
						sentence.remove(lexeme);
						i--;
					}
				}
			}
		}
		logger.info("All the lexemes starting with symbol: " + symbol + " and of length: " + length
		        + " have been removed:");
		return newCompositeText;
	}

	// places sentences of the text in ascending order by number of words in them

	public static CompositeText sentencesByLexemesQuantity(CompositeText compositeText) {
		CompositeText newCompositeText = new CompositeText();

		List<TextComponent> orderedSentences = new ArrayList<TextComponent>();
		for (TextComponent paragraph : compositeText.getComponents()) {
			orderedSentences.addAll(paragraph.getComponents());
		}
		orderedSentences.sort(new CompositeTextSizeComparator());
		for (TextComponent sentence : orderedSentences) {
			newCompositeText.add(sentence);
		}
		logger.info("All the sentences have been put in acsending order by number of lexemes in them:");
		return newCompositeText;

	}
}
