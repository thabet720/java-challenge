package jp.co.axa.apidemo.exception;

public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException() {
		super();
	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}

	public EmployeeNotFoundException(long id) {
		super("employee with id: " + id + " does not exist");
	}

}
