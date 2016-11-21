//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.16 at 06:45:58 PM MSK 
//


package by.training.parser.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InternetTariff complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InternetTariff">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.training.by/TariffsXMLSchema}Tariff">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="unlimitedTraffic" type="{http://www.training.by/TariffsXMLSchema}void"/>
 *           &lt;element name="limitedTrafficMb" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;/choice>
 *         &lt;element name="speed" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InternetTariff", propOrder = {
    "unlimitedTraffic",
    "limitedTrafficMb",
    "speed"
})
public class InternetTariff extends Tariff {

    private boolean unlimitedTraffic;
    private Integer limitedTrafficMb;
    private int speed;

    public boolean getUnlimitedTraffic() {
        return unlimitedTraffic;
    }

    public void setUnlimitedTraffic(boolean value) {
        this.unlimitedTraffic = value;
    }

    public Integer getLimitedTrafficMb() {
        return limitedTrafficMb;
    }

    public void setLimitedTrafficMb(Integer value) {
        this.limitedTrafficMb = value;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int value) {
        this.speed = value;
    }

	@Override
	public String toString() {
		return super.toString() + "\n type: Internet tariff, unlimited traffic: " + unlimitedTraffic + ", limited traffic, Mb: "
		        + limitedTrafficMb + ", speed: " + speed + ".";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((limitedTrafficMb == null) ? 0 : limitedTrafficMb.hashCode());
		result = prime * result + speed;
		result = prime * result + (unlimitedTraffic ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		InternetTariff other = (InternetTariff) obj;
		if (limitedTrafficMb == null) {
			if (other.limitedTrafficMb != null)
				return false;
		} else if (!limitedTrafficMb.equals(other.limitedTrafficMb))
			return false;
		if (speed != other.speed)
			return false;
		if (unlimitedTraffic != other.unlimitedTraffic)
			return false;
		return true;
	}
	
	
}
