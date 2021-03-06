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
 * <p>Java class for CallPrices complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CallPrices">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="internalPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="externalPrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="fixedPhonePrice" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CallPrices", propOrder = {
    "internalPrice",
    "externalPrice",
    "fixedPhonePrice"
})
public class CallPrices {

    private double internalPrice;
    private double externalPrice;
    private double fixedPhonePrice;

    
    public double getInternalPrice() {
        return internalPrice;
    }

    public void setInternalPrice(double value) {
        this.internalPrice = value;
    }

    
    public double getExternalPrice() {
        return externalPrice;
    }

    public void setExternalPrice(double value) {
        this.externalPrice = value;
    }

    public double getFixedPhonePrice() {
        return fixedPhonePrice;
    }

    public void setFixedPhonePrice(double value) {
        this.fixedPhonePrice = value;
    }

	@Override
	public String toString() {
		return "internal call price: " + internalPrice + ", external call price: " + externalPrice
		        + ", fixed phone call price: " + fixedPhonePrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(externalPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(fixedPhonePrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(internalPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CallPrices other = (CallPrices) obj;
		if (Double.doubleToLongBits(externalPrice) != Double.doubleToLongBits(other.externalPrice))
			return false;
		if (Double.doubleToLongBits(fixedPhonePrice) != Double
		        .doubleToLongBits(other.fixedPhonePrice))
			return false;
		if (Double.doubleToLongBits(internalPrice) != Double.doubleToLongBits(other.internalPrice))
			return false;
		return true;
	}

}
