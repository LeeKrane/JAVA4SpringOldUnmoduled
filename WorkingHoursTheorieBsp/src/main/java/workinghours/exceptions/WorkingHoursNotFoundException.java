package workinghours.exceptions;

public class WorkingHoursNotFoundException extends RuntimeException {
	public WorkingHoursNotFoundException () {
	}
	
	public WorkingHoursNotFoundException (String message) {
		super(message);
	}
}
