package by.newnet.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String account;
	private String password;
	private String email;
	private String firstName;
	private String secondName;
	private BigDecimal accountBalance;
	private Role role;
	private boolean blocked;
	private String phone;
	private Tariff tariff;
	
	public boolean isAdmin(){
		if(role.getId() == 1){
			return true;
		}else{
			return false;
		}
	}
	public boolean isOperator(){
		if(role.getId() == 2){
			return true;
		}else{
			return false;
		}
	}
	public boolean isCustomer(){
		if(role.getId() == 3){
			return true;
		}else{
			return false;
		}
	}
	
	public Tariff getTariff() {
		return tariff;
	}
	public void setTariff(Tariff tariff) {
		this.tariff = tariff;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public boolean isBlocked() {
		return blocked;
	}
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
}
