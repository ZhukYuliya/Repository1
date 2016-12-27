package by.newnet.command.exception;

@SuppressWarnings("serial")
public class CommandException extends Exception{

	public CommandException(Exception e){
		super(e);
	}
}
