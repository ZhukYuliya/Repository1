package by.newnet.service.exception;

public class UserAlreadyExistingException extends ServiceException{

private static final long serialVersionUID = 1L;
	
	public UserAlreadyExistingException() {
	}

	public UserAlreadyExistingException(String message) {
		super(message);

	}
	public UserAlreadyExistingException(String message, Exception e) {
		super(message, e);

	}
	
	public UserAlreadyExistingException(Exception e) {
		super(e);

	}
}
