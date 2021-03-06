package model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The Class User.
 */

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String account;
	private String hashPassword;
	private String email;
	private String firstName;
	private String secondName;
	private BigDecimal accountBalance;
	private Role role;
	private boolean blocked;
	private String phone;
	private boolean draft;

	private Tariff tariff;

	/**
	 * Checks if is admin.
	 *
	 * @return true, if is admin
	 */
	public boolean isAdmin() {
		return role == Role.ADMIN;
	}

	/**
	 * Checks if is operator.
	 *
	 * @return true, if is operator
	 */
	public boolean isOperator() {
		return role == Role.OPERATOR;
	}

	/**
	 * Checks if is customer.
	 *
	 * @return true, if is customer
	 */
	public boolean isCustomer() {
		return role == Role.CUSTOMER;
	}

	/**
	 * Gets the tariff.
	 *
	 * @return the tariff
	 */
	public Tariff getTariff() {
		return tariff;
	}

	/**
	 * Sets the tariff.
	 *
	 * @param tariff the new tariff
	 */
	public void setTariff(Tariff tariff) {
		this.tariff = tariff;
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
	 * Gets the account.
	 *
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * Sets the account.
	 *
	 * @param account the new account
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * Gets the hash password.
	 *
	 * @return the hash password
	 */
	public String getHashPassword() {
		return hashPassword;
	}

	/**
	 * Sets the hash password.
	 *
	 * @param hashPassword the new hash password
	 */
	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
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

	/**
	 * Gets the account balance.
	 *
	 * @return the account balance
	 */
	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	/**
	 * Sets the account balance.
	 *
	 * @param accountBalance the new account balance
	 */
	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Checks if is blocked.
	 *
	 * @return true, if is blocked
	 */
	public boolean isBlocked() {
		return blocked;
	}

	/**
	 * Sets the blocked.
	 *
	 * @param blocked the new blocked
	 */
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
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
	 * Checks if is draft.
	 *
	 * @return true, if is draft
	 */
	public boolean isDraft() {
		return draft;
	}

	/**
	 * Sets the draft.
	 *
	 * @param draft the new draft
	 */
	public void setDraft(boolean draft) {
		this.draft = draft;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((accountBalance == null) ? 0 : accountBalance.hashCode());
		result = prime * result + (blocked ? 1231 : 1237);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((hashPassword == null) ? 0 : hashPassword.hashCode());
		result = prime * result + id;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
		result = prime * result + ((tariff == null) ? 0 : tariff.hashCode());
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
		User other = (User) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (accountBalance == null) {
			if (other.accountBalance != null)
				return false;
		} else if (!accountBalance.equals(other.accountBalance))
			return false;
		if (blocked != other.blocked)
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
		if (hashPassword == null) {
			if (other.hashPassword != null)
				return false;
		} else if (!hashPassword.equals(other.hashPassword))
			return false;
		if (id != other.id)
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (secondName == null) {
			if (other.secondName != null)
				return false;
		} else if (!secondName.equals(other.secondName))
			return false;
		if (tariff == null) {
			if (other.tariff != null)
				return false;
		} else if (!tariff.equals(other.tariff))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", account=" + account + ", hashPassword=" + hashPassword
		        + ", email=" + email + ", firstName=" + firstName + ", secondName=" + secondName
		        + ", accountBalance=" + accountBalance + ", role=" + role + ", blocked=" + blocked
		        + ", phone=" + phone + ", tariff=" + tariff + "]";
	}

}
