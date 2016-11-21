package by.training.parser.factory;

import by.training.parser.ITariffsParser;
import by.training.parser.dom.TariffsDOMParser;
import by.training.parser.exception.ParserNotFoundException;
import by.training.parser.sax.TariffsSAXParser;
import by.training.parser.stax.TariffsStAXParser;

public final class TariffsParsersFactory {

    public static final String SAX = "sax";
    public static final String StAX = "stax";
    public static final String DOM = "dom";
    
    private TariffsParsersFactory() {
        
    }
 
    public static ITariffsParser getParserByTypeName(String name) 
            throws ParserNotFoundException {
        switch (name.toLowerCase()) {
        case SAX:
            return TariffsSAXParser.getInstance();
        case StAX:
            return TariffsStAXParser.getInstance();
        case DOM:
            return TariffsDOMParser.getInstance();
        default:
            throw new ParserNotFoundException("Such parser doesn't exist");
        }
    }
}