package jp.co.axa.apidemo.controllers.errorhandling;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jp.co.axa.apidemo.dtos.response.error.ErrorResponse;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;

/**
 * @author ferja This Class will be handling any exception related to the
 *         Employee controller
 */
@ControllerAdvice
public class EmployeeControllerErrorHandler {

	/**
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(EmployeeNotFoundException ex) {
		List<String> errors = new ArrayList<>();
		errors.add(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), errors),
				HttpStatus.NOT_FOUND);
	}

}
