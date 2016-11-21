package by.training.parser.stax;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.training.parser.ITariffsParser;
import by.training.parser.TariffsConstants;
import by.training.parser.exception.XMLParsingException;
import by.training.parser.model.CallPrices;
import by.training.parser.model.InternetTariff;
import by.training.parser.model.MobileTariff;
import by.training.parser.model.Parameters;
import by.training.parser.model.Tariff;

public final class TariffsStAXParser implements ITariffsParser {

	private static final TariffsStAXParser INSTANCE = new TariffsStAXParser();
	private XMLInputFactory inputFactory = XMLInputFactory.newInstance();

	private Tariff currentTariff;
	private CallPrices currentCallPrices;
	private Parameters currentParameters;

	private TariffsStAXParser() {
	}

	public static TariffsStAXParser getInstance() {
		return INSTANCE;
	}

	@Override
	public List<Tariff> parse(String path) throws XMLParsingException {
		try {
			InputStream is = new FileInputStream(path);
			XMLStreamReader reader = inputFactory.createXMLStreamReader(is);
			return parseDocument(reader);
		} catch (XMLStreamException e) {
			throw new XMLParsingException("Creating StAX parser failed ", e);
		} catch (IOException e) {
			throw new XMLParsingException("Reading XML failed", e);
		}
	}

	public List<Tariff> parseDocument(XMLStreamReader reader)
	        throws XMLStreamException, XMLParsingException {
		List<Tariff> tariffs = new LinkedList<>();
		String currentElement = null;
		String localName;
		String tariffType;
		int type;
		while (reader.hasNext()) {
			type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				localName = reader.getLocalName();
				currentElement = localName;
				if (TariffsConstants.TARIFF.equals(localName)) {
					tariffType = reader.getAttributeValue(2);
					switch (tariffType) {
					case TariffsConstants.MOBILE_TARIFF:
						currentTariff = new MobileTariff();
						currentCallPrices = new CallPrices();
						((MobileTariff) currentTariff).setCallPrices(currentCallPrices);
						currentParameters = new Parameters();
						((MobileTariff) currentTariff).setParameters(currentParameters);
						break;
					case TariffsConstants.INTERNET_TARIFF:
						currentTariff = new InternetTariff();

						break;
					default:
						throw new XMLParsingException("Illegal tariff type");
					}

					currentTariff.setId(reader.getAttributeValue(null, TariffsConstants.ID));
					currentTariff.setName(reader.getAttributeValue(null, TariffsConstants.NAME));
				}
				break;

			case XMLStreamConstants.CHARACTERS:
				String s = reader.getText().trim();
				if (currentElement == null) {
					continue;
				}
				switch (currentElement) {
				case TariffsConstants.OPERATOR_NAME:
					currentTariff.setOperatorName(s);
					break;
				case TariffsConstants.PAYROLL:
					currentTariff.setPayroll(Double.valueOf(s));
					break;
				case TariffsConstants.INTERNAL_PRICE:
					((MobileTariff) currentTariff).getCallPrices()
					        .setInternalPrice(Double.valueOf(s));
					break;
				case TariffsConstants.EXTERNAL_PRICE:
					((MobileTariff) currentTariff).getCallPrices()
					        .setExternalPrice(Double.valueOf(s));
					break;
				case TariffsConstants.FIXED_PHONE_PRICE:
					((MobileTariff) currentTariff).getCallPrices()
					        .setFixedPhonePrice(Double.valueOf(s));
					break;
				case TariffsConstants.SMS_PRICE:
					((MobileTariff) currentTariff).setSmsPrice(Double.valueOf(s));
					break;
				case TariffsConstants.FAVOURITE_NUMBERS:
					((MobileTariff) currentTariff).getParameters()
					        .setFavouriteNumbers(Integer.valueOf(s));
					break;
				case TariffsConstants.ADD_TARIFF_FEE:
					((MobileTariff) currentTariff).getParameters()
					        .setAddTariffFee(Double.valueOf(s));
					break;
				case TariffsConstants.TARIFICATION:
					((MobileTariff) currentTariff).getParameters()
					        .setTarification(Integer.valueOf(s));
					break;
				case TariffsConstants.SPEED:
					((InternetTariff) currentTariff).setSpeed(Integer.valueOf(s));
					break;
				case TariffsConstants.UNLIMITED_TRAFFIC:
					((InternetTariff) currentTariff).setUnlimitedTraffic(true);
					break;
				case TariffsConstants.LIMITED_TRAFFIC_MB:
					((InternetTariff) currentTariff).setLimitedTrafficMb(Integer.valueOf(s));
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				localName = reader.getLocalName();
				if (TariffsConstants.UNLIMITED_TRAFFIC.equals(localName)) {
					((InternetTariff) currentTariff).setUnlimitedTraffic(true);
					;
				} else if (TariffsConstants.TARIFF.equals(localName)) {
					tariffs.add(currentTariff);
				} else {
					currentElement = null;
				}
				break;
			}
		}
		return tariffs;

	}
}