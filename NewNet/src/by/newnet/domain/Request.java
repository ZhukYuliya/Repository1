package by.newnet.domain;

import java.io.Serializable;

public class Request implements Comparable<Request>, Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String firstName;
	private String email;
	private String phone;
	private String address;
	//1 =  NEW, 2 = PROCESSED, 3 = CONTRACT_SIGNED
	private int status;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int i) {
		this.status = i;
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
		int compareStatus = ((request.getStatus()));
		//ascending order
		return this.status - compareStatus;
	}
	
	
	
	
}
