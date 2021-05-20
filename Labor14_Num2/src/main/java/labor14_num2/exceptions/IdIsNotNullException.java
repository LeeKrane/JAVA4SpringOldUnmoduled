package labor14_num2.exceptions;

public class IdIsNotNullException extends RuntimeException {
	public IdIsNotNullException () {
	}
	
	public IdIsNotNullException (String message) {
		super(message);
	}
}
