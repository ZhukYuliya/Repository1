package by.training.parser.exception;

public class ParserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ParserNotFoundException() {
	}

	public ParserNotFoundException(String message) {
		super(message);

	}
	public ParserNotFoundException(String message, Exception e) {
		super(message, e);

	}
	
	public ParserNotFoundException(Exception e) {
		super(e);

	}

}
