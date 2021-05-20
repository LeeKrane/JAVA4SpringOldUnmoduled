package labor14_num2.exceptions;

public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException () {
	}
	
	public UserNotFoundException (String message) {
		super(message);
	}
}
