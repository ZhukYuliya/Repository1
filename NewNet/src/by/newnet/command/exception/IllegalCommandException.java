package by.newnet.command.exception;

public class IllegalCommandException extends CommandException{


private static final long serialVersionUID = 1L;
	
	public IllegalCommandException() {
	}

	public IllegalCommandException(String message) {
		super(message);

	}
	public IllegalCommandException(String message, Exception e) {
		super(message, e);

	}
	
	public IllegalCommandException(Exception e) {
		super(e);

	}
}
