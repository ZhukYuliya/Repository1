package by.training.parser;

import java.util.List;

import by.training.parser.exception.XMLParsingException;
import by.training.parser.model.Tariff;

public interface ITariffsParser {
	public List<Tariff> parse(String path) throws XMLParsingException;
}
