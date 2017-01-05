package by.newnet.service.exception;

@SuppressWarnings("serial")
public class ServiceException extends Exception {
	public ServiceException (){}
	public ServiceException (Exception e){
		super (e);
	}
}