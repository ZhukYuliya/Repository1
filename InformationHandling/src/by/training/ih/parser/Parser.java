package by.training.ih.parser;

import by.training.ih.composite.CompositeText;

public interface Parser {
	CompositeText parse (String text);

	Parser getSuccessor();

}
