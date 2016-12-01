//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.16 at 06:45:58 PM MSK 
//


package by.training.parser.model.jaxb;

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
public class InternetTariff
    extends Tariff
{

    protected Void unlimitedTraffic;
    protected Integer limitedTrafficMb;
    protected int speed;

    /**
     * Gets the value of the unlimitedTraffic property.
     * 
     * @return
     *     possible object is
     *     {@link Void }
     *     
     */
    public Void getUnlimitedTraffic() {
        return unlimitedTraffic;
    }

    /**
     * Sets the value of the unlimitedTraffic property.
     * 
     * @param value
     *     allowed object is
     *     {@link Void }
     *     
     */
    public void setUnlimitedTraffic(Void value) {
        this.unlimitedTraffic = value;
    }

    /**
     * Gets the value of the limitedTrafficMb property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLimitedTrafficMb() {
        return limitedTrafficMb;
    }

    /**
     * Sets the value of the limitedTrafficMb property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLimitedTrafficMb(Integer value) {
        this.limitedTrafficMb = value;
    }

    /**
     * Gets the value of the speed property.
     * 
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the value of the speed property.
     * 
     */
    public void setSpeed(int value) {
        this.speed = value;
    }

}