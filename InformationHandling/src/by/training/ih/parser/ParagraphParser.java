package by.training.ih.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.training.ih.composite.CompositeText;
import by.training.ih.composite.ParagraphComposite;


public class ParagraphParser implements Parser{

	public static final ParagraphParser INSTANCE = new ParagraphParser();
	//u2026 stands for "..."
	private static final Pattern SENTENCE_DELIMETER = Pattern.compile(".+?[.!?\\u2026](\\s+|$)"); 

	private SentenceParser successor = SentenceParser.INSTANCE;

	private ParagraphParser(){}
	
	public CompositeText parse(String paragraph) {

		CompositeText compositeParagraphs = new ParagraphComposite();
		Matcher matcher = SENTENCE_DELIMETER.matcher(paragraph);
		while (matcher.find()){
			String sentence = matcher.group().trim();
			CompositeText compositeSentences = getSuccessor().parse(sentence);
			compositeParagraphs.add(compositeSentences);
		}
		return compositeParagraphs;
	}

	@Override
	public Parser getSuccessor() {
		return successor;
	}

}

