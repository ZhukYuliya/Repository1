package by.newnet.service.exception;

@SuppressWarnings("serial")
public class ServiceAuthorizationException extends ServiceException{
	public ServiceAuthorizationException (){}
	public ServiceAuthorizationException (Exception e){
		super (e);
	}
}
