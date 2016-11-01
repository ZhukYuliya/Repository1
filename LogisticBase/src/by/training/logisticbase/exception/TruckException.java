package by.training.logisticbase.exception;

public class TruckException extends Exception {
	private static final long serialVersionUID = 1L;

	public TruckException() {
	}

	public TruckException(String message) {
		super(message);

	}
	public TruckException(String message, Exception e) {
		super(message, e);

	}
	
	public TruckException(Exception e) {
		super(e);

	}

}
