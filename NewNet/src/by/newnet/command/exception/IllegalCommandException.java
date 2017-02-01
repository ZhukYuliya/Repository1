package by.newnet.command.exception;

/**
 * The Class IllegalCommandException.
 */
public class IllegalCommandException extends CommandException{


private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new illegal command exception.
	 */
	public IllegalCommandException() {
	}

	/**
	 * Instantiates a new illegal command exception.
	 *
	 * @param message the message
	 */
	public IllegalCommandException(String message) {
		super(message);

	}
	
	/**
	 * Instantiates a new illegal command exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public IllegalCommandException(String message, Exception e) {
		super(message, e);

	}
	
	/**
	 * Instantiates a new illegal command exception.
	 *
	 * @param e the e
	 */
	public IllegalCommandException(Exception e) {
		super(e);

	}
}
