package by.newnet.domain;

import java.io.Serializable;

public class Request implements Comparable<Request> {
	// private static final long serialVersionUID = 1L;
	private int id;
	private String firstName;
	private String email;
	private String phone;
	private String address;
	private RequestStatus status;

	public RequestStatus getStatus() {
		return status;
	}

	public void setStatus(RequestStatus status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public int compareTo(Request request) {
		//int compareStatus = ((request.getStatus().getStatusCoef()));
		// ascending order
		return this.getStatus().getStatusCoef() - request.getStatus().getStatusCoef();
	}

	public boolean isNew() {
		if (this.getStatus().getStatusCoef() == 1) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isAfterCall() {
		if (this.getStatus().getStatusCoef() == 2) {
			return true;
		} else {
			return false;
		}
	}public boolean isAfterContract() {
		if (this.getStatus().getStatusCoef() == 3) {
			return true;
		} else {
			return false;
		}
	}

}
