package by.training.parser.sax;

import java.io.IOException;
import java.util.List;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.training.parser.ITariffsParser;
import by.training.parser.exception.XMLParsingException;
import by.training.parser.model.Tariff;

public final class TariffsSAXParser implements ITariffsParser{

    private static final TariffsSAXParser INSTANCE = new TariffsSAXParser(); 
    
    private TariffsSAXParser() {
    }
    
    @Override
    public List<Tariff> parse(String path) throws XMLParsingException {
        try {
            XMLReader xmlReader;           
            xmlReader = XMLReaderFactory.createXMLReader();

            TariffsHandler handler = new TariffsHandler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(path));
            return handler.getTariffs();
        } catch (SAXException e){
            throw new XMLParsingException("Creating SAX parser failed ", e);
        } catch (IOException e) {
            throw new XMLParsingException("Reading XML failed", e);
        } 
    }
    
    public static TariffsSAXParser getInstance() {
        return INSTANCE;
    }
}