package by.newnet.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Tariff implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	private BigDecimal price;
	private int speed;
	private int traffic;
	private boolean inactive;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getTraffic() {
		return traffic;
	}
	public void setTraffic(int traffic) {
		this.traffic = traffic;
	}
	public boolean isInactive() {
		return inactive;
	}
	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + (inactive ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + speed;
		result = prime * result + traffic;
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
		Tariff other = (Tariff) obj;
		if (id != other.id)
			return false;
		if (inactive != other.inactive)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (speed != other.speed)
			return false;
		if (traffic != other.traffic)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Tariff [id=" + id + ", name=" + name + ", price=" + price + ", speed=" + speed
		        + ", traffic=" + traffic + ", inactive=" + inactive + "]";
	}
}
	
	