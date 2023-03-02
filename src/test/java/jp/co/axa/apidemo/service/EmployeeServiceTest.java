package jp.co.axa.apidemo.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.axa.apidemo.dtos.EmployeeDTO;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import jp.co.axa.apidemo.services.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class EmployeeServiceTest {
	@InjectMocks
	private EmployeeServiceImpl employeeService;

	@Mock
	private EmployeeRepository employeeRepository;

	@Test
 public void getNoneExistingEmployeeTest() {
	 when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
	 assertThrows(EmployeeNotFoundException.class, ()->{
		 employeeService.getEmployee((long)1);
	 });
 }

	@Test
 public void updateNoneExistingEmployeeTest() {
	 when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
	 assertThrows(EmployeeNotFoundException.class, ()->{
		 employeeService.updateEmployee(new EmployeeDTO(1,"Test",500,"IT"));
	 });
 }
}
