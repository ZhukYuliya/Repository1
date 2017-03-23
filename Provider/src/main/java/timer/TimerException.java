package timer;

/**
 * The Class TimerException.
 */
public class TimerException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new timer exception.
	 */
	public TimerException (){}
	
	/**
	 * Instantiates a new timer exception.
	 *
	 * @param e the e
	 */
	public TimerException (Exception e){
		super (e);
	}
}
