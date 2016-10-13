package by.training.task2.entity;

public abstract class Carriage {
	private String parkName;

	public Carriage(String parkName) {
		this.parkName = parkName;
	}

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parkName == null) ? 0 : parkName.hashCode());
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
		Carriage other = (Carriage) obj;
		if (parkName == null) {
			if (other.parkName != null)
				return false;
		} else if (!parkName.equals(other.parkName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + ", parkName=" + parkName;
	}

}
