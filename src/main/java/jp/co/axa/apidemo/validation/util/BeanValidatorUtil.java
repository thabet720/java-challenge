package jp.co.axa.apidemo.validation.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.axa.apidemo.exception.RequestValidationException;

/**
 * @author ferja This class is used to validate request field that has been
 *         annotated with validation annotation (default or custom)
 */
@Component
public class BeanValidatorUtil {
	private static Validator VALIDATOR;

	@Autowired
	public BeanValidatorUtil(Validator validator) {
		BeanValidatorUtil.VALIDATOR = validator;
	}

	public static <T> void validate(T t) throws RequestValidationException {

		Set<ConstraintViolation<T>> constraintViolations = VALIDATOR.validate(t, Default.class); // checking constraint
																									// violation in <T>
		if (constraintViolations.size() > 0) {
			List<String> errorMsgs = new ArrayList<>(constraintViolations.size());
			for (ConstraintViolation<T> constraintViolation : constraintViolations) {
				errorMsgs
						.add(constraintViolation.getPropertyPath().toString() + " " + constraintViolation.getMessage()); // adding
																															// the
																															// custom
																															// error
																															// message
																															// and
																															// their
																															// respective
																															// field
			}
			throw new RequestValidationException(errorMsgs);
		}
	}

}