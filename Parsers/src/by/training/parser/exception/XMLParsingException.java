package by.training.parser.exception;

public class XMLParsingException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public XMLParsingException() {
	}

	public XMLParsingException(String message) {
		super(message);

	}
	public XMLParsingException(String message, Exception e) {
		super(message, e);

	}
	
	public XMLParsingException(Exception e) {
		super(e);

	}

}
