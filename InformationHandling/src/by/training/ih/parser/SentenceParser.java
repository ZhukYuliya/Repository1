package by.training.ih.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.training.ih.composite.CompositeText;
import by.training.ih.composite.Lexeme;
import by.training.ih.expression.converter.ConvertToPostfix;
import by.training.ih.expression.interpreter.Client;

public class SentenceParser implements Parser{

	public static final SentenceParser INSTANCE = new SentenceParser();

	private static final String DELIMETER = "\\p{Blank}+";
	private static final Pattern DIGITS_AND_OPERATIONS = Pattern.compile("[0-9+\\-*/()]*");
	private static final Pattern OPERATIONS = Pattern.compile("[+\\-*/()]*");

	private SentenceParser() {
	}

	public CompositeText parse(String sentence) {
		CompositeText compositeSentences = new CompositeText();
		String[] lexemes = sentence.split(DELIMETER);
		for (String lexeme : lexemes) {
			lexeme.trim();
			//checks that a lexeme is a math expression OR a number
			Matcher matcher = DIGITS_AND_OPERATIONS.matcher(lexeme);
			if (matcher.matches()){
				// checks that a lexeme is a math expression
				Matcher containsOperatorMatcher = OPERATIONS.matcher(lexeme);
				if (containsOperatorMatcher.find()){
					lexeme = ConvertToPostfix.infixToPostfix(lexeme);
					Client interpreter = new Client(lexeme);
			        lexeme = interpreter.calculate().toString();
				}
			}
			Lexeme newLexeme = new Lexeme(lexeme);
			compositeSentences.add(newLexeme);
		}
		return compositeSentences;

	}

	@Override
	public Parser getSuccessor() {
		throw new UnsupportedOperationException();
	}
	
}
