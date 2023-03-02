package jp.co.axa.apidemo.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jp.co.axa.apidemo.dtos.EmployeeDTO;
import jp.co.axa.apidemo.entities.Employee;

/**
 * @author ferja This converter will convert an EmployeeEntity to EmployeeDTO so
 *         that we minimize the coupling between then two.
 */
@Component
public class EmployeeEntityToEmployeeDTO implements Converter<Employee, EmployeeDTO> {

	@Override
	public EmployeeDTO convert(Employee arg0) {
		EmployeeDTO emp = new EmployeeDTO();
		emp.setId(arg0.getId());
		emp.setName(arg0.getName());
		emp.setSalary(arg0.getSalary());
		emp.setDepartment(arg0.getDepartment());
		return emp;
	}

}
