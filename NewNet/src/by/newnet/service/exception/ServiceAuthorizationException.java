package by.newnet.service.exception;

public class ServiceAuthorizationException extends ServiceException{

private static final long serialVersionUID = 1L;
	
	public ServiceAuthorizationException() {
	}

	public ServiceAuthorizationException(String message) {
		super(message);

	}
	public ServiceAuthorizationException(String message, Exception e) {
		super(message, e);

	}
	
	public ServiceAuthorizationException(Exception e) {
		super(e);

	}
}
