package by.training.task2.entity;

public class PassengerCarriage extends Carriage {
	private ComfortLevel comfortLevel;
	private int passengerNumber;
	private int luggageNumber;

	public PassengerCarriage(String parkName, ComfortLevel comfortLevel, int passengerNumber,
	        int luggageNumber) {
		super(parkName);
		this.comfortLevel = comfortLevel;
		this.passengerNumber = passengerNumber;
		this.luggageNumber = luggageNumber;
	}

	public ComfortLevel getComfortLevel() {
		return comfortLevel;
	}

	public void setComfortLevel(ComfortLevel comfortLevel) {
		this.comfortLevel = comfortLevel;
	}

	public int getPassengerNumber() {
		return passengerNumber;
	}

	public void setPassengerNumber(int passengerNumber) {
		this.passengerNumber = passengerNumber;
	}

	public int getLuggageNumber() {
		return luggageNumber;
	}

	public void setLuggageNumber(int luggageNumber) {
		this.luggageNumber = luggageNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((comfortLevel == null) ? 0 : comfortLevel.hashCode());
		result = prime * result + luggageNumber;
		result = prime * result + passengerNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PassengerCarriage other = (PassengerCarriage) obj;
		if (comfortLevel != other.comfortLevel)
			return false;
		if (luggageNumber != other.luggageNumber)
			return false;
		if (passengerNumber != other.passengerNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + ", comfortLevel=" + comfortLevel + ", passengerNumber="
		        + passengerNumber + ", luggageNumber=" + luggageNumber;
	}

}
