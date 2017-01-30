package by.newnet.model;

public enum RequestStatus {
	NEW(1), AFTER_CALL(2), AFTER_CONTRACT(3);

	private int statusCoef;

	RequestStatus() {
	}

	RequestStatus(int statusCoef) {
		this.statusCoef = statusCoef;
	}

	public int getStatusCoef() {
		return statusCoef;
	}

	// public?
	public void setStatusCoef(int statusCoef) {
		this.statusCoef = statusCoef;
	}

	@Override
	public String toString() {
		return name();
	}
}
