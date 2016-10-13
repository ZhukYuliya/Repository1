package by.training.task2.exception;

public class NoSuchTrainException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public NoSuchTrainException(){}
	
	public NoSuchTrainException(String message){
		super(message);
		
	}
}
