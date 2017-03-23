package command.exception;

/**
 * The Class IllegalCommandException.
 */
public class CommandNotFoundException extends CommandException{


private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new illegal command exception.
	 */
	public CommandNotFoundException() {
	}

	/**
	 * Instantiates a new illegal command exception.
	 *
	 * @param message the message
	 */
	public CommandNotFoundException(String message) {
		super(message);

	}
	
	/**
	 * Instantiates a new illegal command exception.
	 *
	 * @param message the message
	 * @param e the e
	 */
	public CommandNotFoundException(String message, Exception e) {
		super(message, e);

	}
	
	/**
	 * Instantiates a new illegal command exception.
	 *
	 * @param e the e
	 */
	public CommandNotFoundException(Exception e) {
		super(e);

	}
}
