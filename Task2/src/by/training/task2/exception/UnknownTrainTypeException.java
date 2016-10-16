package by.training.task2.exception;

public class UnknownTrainTypeException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public UnknownTrainTypeException(){}
	
	public UnknownTrainTypeException(String message){
		super(message);
		
	}
}
