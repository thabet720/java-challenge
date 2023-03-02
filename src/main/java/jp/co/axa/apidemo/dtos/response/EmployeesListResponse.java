package jp.co.axa.apidemo.dtos.response;

import java.util.List;

import jp.co.axa.apidemo.dtos.EmployeeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeesListResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<EmployeeDTO> employees;

}
