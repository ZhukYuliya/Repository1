package model;

/**
 * The Enum RequestStatus.
 */
public enum RequestStatus {
	NEW(1), AFTER_CALL(2), AFTER_CONTRACT(3);

	private int statusCoef;

	/**
	 * Instantiates a new request status.
	 */
	RequestStatus() {
	}

	/**
	 * Instantiates a new request status.
	 *
	 * @param statusCoef the status coef
	 */
	RequestStatus(int statusCoef) {
		this.statusCoef = statusCoef;
	}

	/**
	 * Gets the status coef.
	 *
	 * @return the status coef
	 */
	public int getStatusCoef() {
		return statusCoef;
	}

	/**
	 * Sets the status coef.
	 *
	 * @param statusCoef the new status coef
	 */

	public void setStatusCoef(int statusCoef) {
		this.statusCoef = statusCoef;
	}

	@Override
	public String toString() {
		return name();
	}
}
