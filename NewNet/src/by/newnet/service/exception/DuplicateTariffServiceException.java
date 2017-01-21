package by.newnet.service.exception;

@SuppressWarnings("serial")
public class DuplicateTariffServiceException extends Exception {
	public DuplicateTariffServiceException (){}
	public DuplicateTariffServiceException (Exception e){
		super (e);
	}
}