package model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The Class CreditCard.
 */
public class CreditCard implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String number;
	private String expirationMonth;
	private String expirationYear;
	private String securityCode;
	private String firstName;
	private String secondName;
	private BigDecimal balance;

	/**
	 * Gets the balance.
	 *
	 * @return the balance
	 */
	public BigDecimal getBalance() {
		return balance;
	}
	
	/**
	 * Sets the balance.
	 *
	 * @param balance the new balance
	 */
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * Sets the number.
	 *
	 * @param number the new number
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	/**
	 * Gets the expiration month.
	 *
	 * @return the expiration month
	 */
	public String getExpirationMonth() {
		return expirationMonth;
	}
	
	/**
	 * Sets the expiration month.
	 *
	 * @param expirationMonth the new expiration month
	 */
	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	
	/**
	 * Gets the expiration year.
	 *
	 * @return the expiration year
	 */
	public String getExpirationYear() {
		return expirationYear;
	}
	
	/**
	 * Sets the expiration year.
	 *
	 * @param expirationYear the new expiration year
	 */
	public void setExpirationYear(String expirationYear) {
		this.expirationYear = expirationYear;
	}
	
	/**
	 * Gets the security code.
	 *
	 * @return the security code
	 */
	public String getSecurityCode() {
		return securityCode;
	}
	
	/**
	 * Sets the security code.
	 *
	 * @param securityCode the new security code
	 */
	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}
	
	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * Gets the second name.
	 *
	 * @return the second name
	 */
	public String getSecondName() {
		return secondName;
	}
	
	/**
	 * Sets the second name.
	 *
	 * @param secondName the new second name
	 */
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expirationMonth == null) ? 0 : expirationMonth.hashCode());
		result = prime * result + ((expirationYear == null) ? 0 : expirationYear.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
		result = prime * result + ((securityCode == null) ? 0 : securityCode.hashCode());
		return result;
	}
	
	/**
	 * Compares by all the fields except for balance as the purpose is to compare the permanent card details.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreditCard other = (CreditCard) obj;
		if (expirationMonth == null) {
			if (other.expirationMonth != null)
				return false;
		} else if (!expirationMonth.equals(other.expirationMonth))
			return false;
		if (expirationYear == null) {
			if (other.expirationYear != null)
				return false;
		} else if (!expirationYear.equals(other.expirationYear))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (secondName == null) {
			if (other.secondName != null)
				return false;
		} else if (!secondName.equals(other.secondName))
			return false;
		if (securityCode == null) {
			if (other.securityCode != null)
				return false;
		} else if (!securityCode.equals(other.securityCode))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "CreditCard [number=" + number + ", expirationMonth=" + expirationMonth
		        + ", expirationYear=" + expirationYear + ", securityCode=" + securityCode
		        + ", firstName=" + firstName + ", secondName=" + secondName + ", balance=" + balance
		        + "]";
	}
	
}
	
	