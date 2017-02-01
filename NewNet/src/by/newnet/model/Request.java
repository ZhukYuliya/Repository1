package by.newnet.model;

import java.io.Serializable;

/**
 * The Class Request.
 */
public class Request implements Comparable<Request>, Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String firstName;
	private String email;
	private String phone;
	private String address;
	private RequestStatus status;

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public RequestStatus getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
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
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int compareTo(Request request) {
		return this.getStatus().getStatusCoef() - request.getStatus().getStatusCoef();
	}

	/**
	 * Checks if is new.
	 *
	 * @return true, if is new
	 */
	public boolean isNew() {
		return this.getStatus() == RequestStatus.NEW;
	}

	/**
	 * Checks if is after call.
	 *
	 * @return true, if is after call
	 */
	public boolean isAfterCall() {
		return this.getStatus() == RequestStatus.AFTER_CALL;

	}

	/**
	 * Checks if is after contract.
	 *
	 * @return true, if is after contract
	 */
	public boolean isAfterContract() {
		return this.getStatus() == RequestStatus.AFTER_CONTRACT;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Request other = (Request) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", firstName=" + firstName + ", email=" + email + ", phone="
		        + phone + ", address=" + address + ", status=" + status + "]";
	}

}
