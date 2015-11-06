package exceptions;

public class NotFoundException extends Exception
{
		// we need this to tell Java that the objects can be saved if required
	static final long serialVersionUID = 100L;

	public NotFoundException() {
		super();
	}
	public NotFoundException (String message) {
		super (message);
	}
}
