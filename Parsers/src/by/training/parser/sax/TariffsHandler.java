package by.training.parser.sax;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.training.parser.TariffsConstants;
import by.training.parser.model.CallPrices;
import by.training.parser.model.InternetTariff;
import by.training.parser.model.MobileTariff;
import by.training.parser.model.Parameters;
import by.training.parser.model.Tariff;

public class TariffsHandler extends DefaultHandler {

	private List<Tariff> tariffs;
	private Tariff currentTariff;
	private CallPrices currentCallPrices;
	private Parameters currentParameters;

	private String currentElement;

	@Override
	public void startDocument() throws SAXException {
		tariffs = new ArrayList<Tariff>();
	}

	@Override
	public void startElement(String namespaceURI, String localName, String qName,
	        Attributes attributes) throws SAXException {
		String type = attributes.getValue(TariffsConstants.TYPE);
		if (localName.equals(TariffsConstants.TARIFF) && type != null) {
			switch (type) {
			case TariffsConstants.MOBILE_TARIFF:
				currentTariff = new MobileTariff();
				currentCallPrices = new CallPrices();
				currentParameters = new Parameters();
				break;

			case TariffsConstants.INTERNET_TARIFF:
				currentTariff = new InternetTariff();
				break;
			}
		
			currentTariff.setId(attributes.getValue(TariffsConstants.ID));
			currentTariff.setName(attributes.getValue(TariffsConstants.NAME));
			// tag unlimitedTraffic is checked here, not in characters method, because the tag 
			// itself doesn't contain  characters
		} else if (localName.equals(TariffsConstants.UNLIMITED_TRAFFIC)){
			((InternetTariff)currentTariff).setUnlimitedTraffic(true);
		}else{
			currentElement = localName;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		String s = new String(ch, start, length).trim();
		if (currentElement == null) {
			return;
		}
		switch (currentElement) {
		case TariffsConstants.OPERATOR_NAME:
			currentTariff.setOperatorName(s);
			break;
		case TariffsConstants.PAYROLL:
			currentTariff.setPayroll(Double.valueOf(s));
			break;
		case TariffsConstants.INTERNAL_PRICE:
			currentCallPrices.setInternalPrice(Double.valueOf(s));
			break;
		case TariffsConstants.EXTERNAL_PRICE:
			currentCallPrices.setExternalPrice(Double.valueOf(s));
			break;
		case TariffsConstants.FIXED_PHONE_PRICE:
			currentCallPrices.setFixedPhonePrice(Double.valueOf(s));
			break;
		case TariffsConstants.SMS_PRICE:
			((MobileTariff) currentTariff).setSmsPrice(Double.valueOf(s));
			break;
		case TariffsConstants.FAVOURITE_NUMBERS:
			currentParameters.setFavouriteNumbers(Integer.valueOf(s));
			break;
		case TariffsConstants.ADD_TARIFF_FEE:
			currentParameters.setAddTariffFee(Double.valueOf(s));
			break;
		case TariffsConstants.TARIFICATION:
			currentParameters.setTarification(Integer.valueOf(s));
			break;
		case TariffsConstants.LIMITED_TRAFFIC_MB:
			((InternetTariff) currentTariff).setLimitedTrafficMb(Integer.valueOf(s));
			break;
		case TariffsConstants.SPEED:
			((InternetTariff) currentTariff).setSpeed(Integer.valueOf(s));
			break;
		}

	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName)
	        throws SAXException {
		switch (localName) {
		case TariffsConstants.CALL_PRICES:
			((MobileTariff) currentTariff).setCallPrices(currentCallPrices);
			break;
		case TariffsConstants.PARAMETERS:
			((MobileTariff) currentTariff).setParameters(currentParameters);
			break;
		case TariffsConstants.TARIFF:
			tariffs.add(currentTariff);
			break;
		default:
			currentElement = null;
		}
	}

	public List<Tariff> getTariffs() {
		return tariffs;
	}
}
