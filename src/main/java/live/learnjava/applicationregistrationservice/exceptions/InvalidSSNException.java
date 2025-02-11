package live.learnjava.applicationregistrationservice.exceptions;

@SuppressWarnings("serial")
public class InvalidSSNException extends Exception {
	public InvalidSSNException() {
		super();
	}
	public InvalidSSNException(String msg) {
		super(msg);
	}
}
