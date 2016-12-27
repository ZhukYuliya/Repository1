package by.newnet.service.exception;

@SuppressWarnings("serial")
public class UserAlreadyExistingException extends ServiceException{
	public UserAlreadyExistingException (){}
	public UserAlreadyExistingException (Exception e){
		super (e);
	}
}
