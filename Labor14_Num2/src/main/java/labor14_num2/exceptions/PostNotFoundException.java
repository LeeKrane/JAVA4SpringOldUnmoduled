package labor14_num2.exceptions;

public class PostNotFoundException extends RuntimeException {
	public PostNotFoundException () {
	}
	
	public PostNotFoundException (String message) {
		super(message);
	}
}
