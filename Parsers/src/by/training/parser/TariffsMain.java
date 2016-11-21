package by.training.parser;

import java.util.List;

import org.apache.log4j.Logger;

import by.training.parser.exception.ParserNotFoundException;
import by.training.parser.exception.XMLParsingException;
import by.training.parser.factory.TariffsParsersFactory;
import by.training.parser.model.Tariff;

public class TariffsMain {

	private final static Logger logger = Logger.getLogger(TariffsMain.class);
	
	private final static String path = "xml/Tariffs.xml";

	public static void main(String[] args) throws ParserNotFoundException, XMLParsingException {

		try {
			ITariffsParser SAXparser =
			        TariffsParsersFactory.getParserByTypeName(TariffsParsersFactory.SAX);
			List<Tariff> listSAX = SAXparser.parse(path);
			for (Tariff tariff : listSAX) {
				System.out.println(tariff);
			}
			System.out.println("\n");

			ITariffsParser DOMparser =
			        TariffsParsersFactory.getParserByTypeName(TariffsParsersFactory.DOM);
			List<Tariff> listDOM = DOMparser.parse(path);
			for (Tariff tariff : listDOM) {
				System.out.println(tariff);
			}
			System.out.println("\n");

			ITariffsParser StAXparser =
			        TariffsParsersFactory.getParserByTypeName(TariffsParsersFactory.StAX);
			List<Tariff> listStAx = StAXparser.parse(path);
			for (Tariff tariff : listStAx) {
				System.out.println(tariff);
			}
		} catch (ParserNotFoundException | XMLParsingException e) {
			logger.error(e.getMessage(), e);
		}

	}
}
