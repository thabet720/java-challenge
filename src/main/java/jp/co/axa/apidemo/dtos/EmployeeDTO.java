package jp.co.axa.apidemo.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ferja this class is used so that we minimize the coupling between our
 *         entity and the respective API response. Sometimes we don't want to
 *         expose all of the entities fields in the response
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	@NotBlank(message = "must not be null or empty")
	private String name;

	@Positive(message = "must be positive")
	private int salary;

	@NotBlank(message = "must not be null or empty")
	private String department;

	public EmployeeDTO(String name, int salary, String department) {
		super();
		this.name = name;
		this.salary = salary;
		this.department = department;
	}

}
