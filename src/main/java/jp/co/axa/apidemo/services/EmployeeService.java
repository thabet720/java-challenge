package jp.co.axa.apidemo.services;

import java.util.List;

import jp.co.axa.apidemo.dtos.EmployeeDTO;

public interface EmployeeService {

	public List<EmployeeDTO> retrieveEmployees(int pageNo, int pageSize);

	public EmployeeDTO getEmployee(Long employeeId);

	public EmployeeDTO saveEmployee(EmployeeDTO employee);

	public void deleteEmployee(Long employeeId);

	public EmployeeDTO updateEmployee(EmployeeDTO employee);
}