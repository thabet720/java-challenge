package jp.co.axa.apidemo.dtos.response.error;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ferja
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {
	private int status;
	private List<String> message;

}
