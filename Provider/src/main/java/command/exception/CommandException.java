package command.exception;

/**
 * The Class CommandException.
 */
public class CommandException extends Exception{

private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new command exception.
	 */
	public CommandException() {
	}

	/**
	 * Instantiates a new command exception.
	 *
	 * @param message the message
	 */
	public CommandException(String message) {
		super(message);

	}
	
	/**
	 * Instantiates a new command exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public CommandException(String message, Exception e) {
		super(message, e);

	}
	
	/**
	 * Instantiates a new command exception.
	 *
	 * @param e the e
	 */
	public CommandException(Exception e) {
		super(e);

	}
}
