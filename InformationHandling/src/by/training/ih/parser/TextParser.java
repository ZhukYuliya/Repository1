package by.training.ih.parser;

import by.training.ih.composite.CompositeText;

public class TextParser implements Parser{

	public static final TextParser INSTANCE = new TextParser(); 
	
	private static final String PARAGRAPH_DELIMETER = "\\n";
	
	private ParagraphParser successor = ParagraphParser.INSTANCE;

	private TextParser(){}
	
	public CompositeText parse(String text) {
		CompositeText compositeText = new CompositeText();
		String[] paragraphs = text.split(PARAGRAPH_DELIMETER);
		for (String paragraph : paragraphs) {
			CompositeText compositeParagraphs = getSuccessor().parse(paragraph);
			compositeText.add(compositeParagraphs);
		}
		return compositeText;
	}
	
	@Override
	public ParagraphParser getSuccessor() {
		return successor;
	}
}
