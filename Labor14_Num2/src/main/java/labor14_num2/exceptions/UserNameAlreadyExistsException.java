package labor14_num2.exceptions;

public class UserNameAlreadyExistsException extends RuntimeException {
	public UserNameAlreadyExistsException () {
	}
	
	public UserNameAlreadyExistsException (String message) {
		super(message);
	}
}
