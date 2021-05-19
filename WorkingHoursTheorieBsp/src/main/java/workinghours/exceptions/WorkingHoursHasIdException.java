package workinghours.exceptions;

public class WorkingHoursHasIdException extends RuntimeException {
	public WorkingHoursHasIdException () {
	}
	
	public WorkingHoursHasIdException (String message) {
		super(message);
	}
}
