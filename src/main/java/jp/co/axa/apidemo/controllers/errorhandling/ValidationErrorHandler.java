package jp.co.axa.apidemo.controllers.errorhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jp.co.axa.apidemo.dtos.response.error.ErrorResponse;
import jp.co.axa.apidemo.exception.RequestValidationException;

/**
 * @author ferja This Class will be handling any validation exception for any
 *         controller
 */
@ControllerAdvice
public class ValidationErrorHandler {
	/**
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value = RequestValidationException.class)
	public ResponseEntity<ErrorResponse> notFoundException(RequestValidationException ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessageList());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

}
