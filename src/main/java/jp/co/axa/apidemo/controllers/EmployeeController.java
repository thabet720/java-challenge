package jp.co.axa.apidemo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.co.axa.apidemo.dtos.EmployeeDTO;
import jp.co.axa.apidemo.dtos.response.EmployeesListResponse;
import jp.co.axa.apidemo.dtos.response.SingleEmployeeResponse;
import jp.co.axa.apidemo.exception.RequestValidationException;
import jp.co.axa.apidemo.services.EmployeeService;
import jp.co.axa.apidemo.validation.util.BeanValidatorUtil;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping("/employees")
	public ResponseEntity<EmployeesListResponse> getEmployees(@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize) {
		List<EmployeeDTO> employees = employeeService.retrieveEmployees(pageNo, pageSize);
		EmployeesListResponse response = new EmployeesListResponse(employees);
		response.setMessage("Success");
		response.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<>(response, HttpStatus.resolve(response.getStatus()));
	}

	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<SingleEmployeeResponse> getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		SingleEmployeeResponse response = new SingleEmployeeResponse(employeeService.getEmployee(employeeId));
		response.setMessage("Success");
		response.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<SingleEmployeeResponse>(response, HttpStatus.resolve(response.getStatus()));
	}

	@PostMapping("/employees")
	public ResponseEntity<SingleEmployeeResponse> saveEmployee(@RequestBody EmployeeDTO employee) {
		if (employee.getId() > 0) {
			throw new RequestValidationException("Employee id must not be sent in a create employee request");
		}
		BeanValidatorUtil.validate(employee);
		SingleEmployeeResponse response = new SingleEmployeeResponse(employeeService.saveEmployee(employee));
		response.setMessage("Success");
		response.setStatus(HttpStatus.CREATED.value());
		return new ResponseEntity<SingleEmployeeResponse>(response, HttpStatus.resolve(response.getStatus()));

	}

	@DeleteMapping("/employees/{employeeId}")
	public ResponseEntity<SingleEmployeeResponse> deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		employeeService.deleteEmployee(employeeId);
		SingleEmployeeResponse response = new SingleEmployeeResponse();
		response.setMessage("Success");
		response.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<SingleEmployeeResponse>(response, HttpStatus.resolve(response.getStatus()));
	}

	@PutMapping("/employees/{employeeId}")
	public ResponseEntity<SingleEmployeeResponse> updateEmployee(@RequestBody EmployeeDTO employee,
			@PathVariable(name = "employeeId") Long employeeId) {
		BeanValidatorUtil.validate(employee);
		employee.setId(employeeId);
		SingleEmployeeResponse response = new SingleEmployeeResponse(employeeService.updateEmployee(employee));
		response.setMessage("Success");
		response.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<SingleEmployeeResponse>(response, HttpStatus.resolve(response.getStatus()));

	}

}
