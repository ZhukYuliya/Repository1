package by.newnet.service.exception;

public class DuplicateTariffServiceException extends Exception {

private static final long serialVersionUID = 1L;
	
	public DuplicateTariffServiceException() {
	}

	public DuplicateTariffServiceException(String message) {
		super(message);

	}
	public DuplicateTariffServiceException(String message, Exception e) {
		super(message, e);

	}
	
	public DuplicateTariffServiceException(Exception e) {
		super(e);

	}
}