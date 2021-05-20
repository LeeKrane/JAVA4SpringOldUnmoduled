package labor14_num2.exceptions;

public class IdIsNullException extends RuntimeException {
	public IdIsNullException () {
	}
	
	public IdIsNullException (String message) {
		super(message);
	}
}
