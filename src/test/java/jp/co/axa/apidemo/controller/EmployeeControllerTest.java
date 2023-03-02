package jp.co.axa.apidemo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.dtos.EmployeeDTO;
import jp.co.axa.apidemo.dtos.response.SingleEmployeeResponse;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.exception.RequestValidationException;
import jp.co.axa.apidemo.services.EmployeeService;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeControllerTest {
	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeService employeeService;

	@Test
	public void addEmployeeTest() {
		ResponseEntity<SingleEmployeeResponse> responseEntity = executeSaveAndUpdateRequest(0, "test", 500, "IT");
		assertThat(responseEntity.getStatusCode() == HttpStatus.CREATED);
		assertThat(responseEntity.getBody().getStatus() == HttpStatus.CREATED.value());
		assertThat(responseEntity.getBody().getEmployee().getName().equals("test"));
		assertThat(responseEntity.getBody().getEmployee().getDepartment().equals("IT"));
		assertThat(responseEntity.getBody().getEmployee().getSalary() == 500);
	}

	@Test
	public void addEmployeeWithNegativeSalaryTest() {
		assertThrows(RequestValidationException.class, () -> {
			executeSaveAndUpdateRequest(0, "test", -500, "IT");
		});
	}

	@Test
	public void addEmployeeWithBlankNameTest() {
		assertThrows(RequestValidationException.class, () -> {
			executeSaveAndUpdateRequest(0, "", 500, "IT");
		});

	}

	@Test
	public void addEmployeeWithNullNameTest() {
		assertThrows(RequestValidationException.class, () -> {
			executeSaveAndUpdateRequest(0, null, 500, "IT");
		});

	}

	@Test
	public void addEmployeeWithBlankDepartementTest() {
		assertThrows(RequestValidationException.class, () -> {
			executeSaveAndUpdateRequest(0, "test", 500, "");
		});

	}

	@Test
	public void addEmployeeWithNullDepartementTest() {
		assertThrows(RequestValidationException.class, () -> {
			executeSaveAndUpdateRequest(0, "test", 500, null);
		});

	}

	@Test
	public void updateEmployeeTest() {
		ResponseEntity<SingleEmployeeResponse> response = executeSaveAndUpdateRequest(1, "Test", 500, "IT");
		assertThat(response.getStatusCode().equals(HttpStatus.OK));
		assertThat(response.getBody().getStatus() == HttpStatus.OK.value());
		assertThat(response.getBody().getEmployee().getId() == 1);
	}

	@Test
	public void deleteNoneExistingEmployeeTest() {
		doThrow(EmployeeNotFoundException.class).when(employeeService).deleteEmployee(anyLong());
		assertThrows(EmployeeNotFoundException.class, () -> {
			employeeController.deleteEmployee((long) 1);
		});

	}

	private ResponseEntity<SingleEmployeeResponse> executeSaveAndUpdateRequest(long id, String name, int salary,
			String departement) {
		EmployeeDTO requestBody = null;
		if (id == 0) {
			requestBody = new EmployeeDTO(name, salary, departement);
			when(employeeService.saveEmployee(requestBody)).thenReturn(requestBody);
			return employeeController.saveEmployee(requestBody);
		} else {
			requestBody = new EmployeeDTO(name, salary, departement);
			when(employeeService.updateEmployee(requestBody)).thenReturn(requestBody);
			return employeeController.updateEmployee(requestBody, id);
		}

	}

}
