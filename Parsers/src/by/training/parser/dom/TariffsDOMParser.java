package by.training.parser.dom;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import by.training.parser.ITariffsParser;
import by.training.parser.TariffsConstants;
import by.training.parser.exception.XMLParsingException;
import by.training.parser.model.CallPrices;
import by.training.parser.model.InternetTariff;
import by.training.parser.model.MobileTariff;
import by.training.parser.model.Parameters;
import by.training.parser.model.Tariff;

public final class TariffsDOMParser implements ITariffsParser {
	
	private static final TariffsDOMParser INSTANCE = new TariffsDOMParser();

	private TariffsDOMParser() {
	}
	public static TariffsDOMParser getInstance() {
		return INSTANCE;
	}
	@Override
	public List<Tariff> parse(String path) throws XMLParsingException {
		try {
			DOMParser domParser = new DOMParser();
			domParser.parse(new InputSource(path));
			Document root = domParser.getDocument();
			return parseDocument(root);
		} catch (SAXException e){
            throw new XMLParsingException("Creating DOM parser failed ", e);
        } catch (IOException e) {
            throw new XMLParsingException("Reading XML failed", e);
        }
	}

	private List<Tariff> parseDocument(Document document) {
		List<Tariff> tariffsList = new LinkedList<>();
		Tariff currentTariff = null;
		NodeList tariffNodes = document.getElementsByTagName(TariffsConstants.TARIFF);
		for (int i = 0; i < tariffNodes.getLength(); i++) {
			if (tariffNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element tariff = (Element) tariffNodes.item(i);
				if ((tariff.getAttribute(TariffsConstants.TYPE))
				        .equals(TariffsConstants.MOBILE_TARIFF)) {
					currentTariff = parseMobileTariff(tariff);
				} else {
				 currentTariff = parseInternetTariff(tariff);
				}
			}
			tariffsList.add(currentTariff);
		}
		return tariffsList;
	}

	private MobileTariff parseMobileTariff(Element tariffElement) {
		MobileTariff currentMobileTariff = new MobileTariff();
		setCommonElements(currentMobileTariff, tariffElement);
		CallPrices currentCallPrices = new CallPrices();
		Element callPrices =
		        (Element) tariffElement.getElementsByTagName(TariffsConstants.CALL_PRICES).item(0);
		Element internalPrice =
		        (Element) callPrices.getElementsByTagName(TariffsConstants.INTERNAL_PRICE).item(0);
		Element externalPrice =
		        (Element) callPrices.getElementsByTagName(TariffsConstants.EXTERNAL_PRICE).item(0);
		Element fixedPhonePrice = (Element) callPrices
		        .getElementsByTagName(TariffsConstants.FIXED_PHONE_PRICE).item(0);
		currentCallPrices.setInternalPrice(Double.valueOf(internalPrice.getTextContent()));
		currentCallPrices.setExternalPrice(Double.valueOf(externalPrice.getTextContent()));
		currentCallPrices.setFixedPhonePrice(Double.valueOf(fixedPhonePrice.getTextContent()));
		currentMobileTariff.setCallPrices(currentCallPrices);
		Element smsPrice =
		        (Element) tariffElement.getElementsByTagName(TariffsConstants.SMS_PRICE).item(0);
		currentMobileTariff.setSmsPrice(Double.valueOf(smsPrice.getTextContent()));
		Parameters currentParameters = new Parameters();
		Element parameters =
		        (Element) tariffElement.getElementsByTagName(TariffsConstants.PARAMETERS).item(0);
		Element favouriteNumbers = (Element) parameters
		        .getElementsByTagName(TariffsConstants.FAVOURITE_NUMBERS).item(0);
		Element addTariffFee =
		        (Element) parameters.getElementsByTagName(TariffsConstants.ADD_TARIFF_FEE).item(0);
		Element tarification = (Element) parameters
		        .getElementsByTagName(TariffsConstants.TARIFICATION).item(0);
		currentParameters.setFavouriteNumbers(Integer.valueOf(favouriteNumbers.getTextContent()));
		currentParameters.setAddTariffFee(Double.valueOf(addTariffFee.getTextContent()));
		currentParameters.setTarification(Integer.valueOf(tarification.getTextContent()));
		currentMobileTariff.setParameters(currentParameters);

		return currentMobileTariff;
	}

	private InternetTariff parseInternetTariff(Element tariffElement) {
		InternetTariff currentInternetTariff = new InternetTariff();
		setCommonElements(currentInternetTariff, tariffElement);
		NodeList trafficNode =
		        tariffElement.getElementsByTagName(TariffsConstants.LIMITED_TRAFFIC_MB);
		if (trafficNode.getLength() != 0) {
			currentInternetTariff
			        .setLimitedTrafficMb(Integer.valueOf(trafficNode.item(0).getTextContent()));
		} else {
			currentInternetTariff.setUnlimitedTraffic(true);
		}
		Element speed = (Element)tariffElement.getElementsByTagName(TariffsConstants.SPEED).item(0);
		currentInternetTariff.setSpeed(Integer.valueOf(speed.getFirstChild().getTextContent()));
		
		return currentInternetTariff;
	}
	
	private void setCommonElements(Tariff currentTariff, Element tariffElement) {
		currentTariff.setId(tariffElement.getAttribute(TariffsConstants.ID));
		currentTariff.setName(tariffElement.getAttribute(TariffsConstants.NAME));
		Element operatorName = (Element) tariffElement
		        .getElementsByTagName(TariffsConstants.OPERATOR_NAME).item(0);
		currentTariff.setOperatorName(operatorName.getFirstChild().getTextContent());
		Element payroll = (Element) tariffElement
		        .getElementsByTagName(TariffsConstants.PAYROLL).item(0);
		currentTariff.setPayroll(Double.valueOf(payroll.getFirstChild().getTextContent()));
	}
}
