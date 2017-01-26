package by.newnet.domain;

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
