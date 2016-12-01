package by.training.ih.reader;

public class TxtReaderException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public TxtReaderException() {
	}

	public TxtReaderException(String message) {
		super(message);

	}
	public TxtReaderException(String message, Exception e) {
		super(message, e);

	}
	
	public TxtReaderException(Exception e) {
		super(e);

	}
	
}
