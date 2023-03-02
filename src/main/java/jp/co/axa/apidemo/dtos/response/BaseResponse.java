package jp.co.axa.apidemo.dtos.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ferja
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status;
	private String message;

}
