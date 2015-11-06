package exceptions;

public class DuplicateException extends Exception {
		// we need this to tell Java that the objects can be saved if required
	static final long serialVersionUID = 100L;

	public DuplicateException() {
		super();
	}
	public DuplicateException (String message) {
		super (message);
	}
}
