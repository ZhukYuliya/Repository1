package by.training.task2.entity;

public class CargoCarriage extends Carriage{
	private int cargoCapacity;

	public CargoCarriage(String parkName, int cargoCapacity ){
		super (parkName);
		this.cargoCapacity = cargoCapacity;
	}
	public int getCapacity() {
		return cargoCapacity;
	}
	public void setCapacity(int capacity) {
		this.cargoCapacity = capacity;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + cargoCapacity;
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
		CargoCarriage other = (CargoCarriage) obj;
		if (cargoCapacity != other.cargoCapacity)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return super.toString() + ", cargoCapacity=" + cargoCapacity;
	}
	
}
