package jp.co.axa.apidemo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.dtos.EmployeeDTO;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ConversionService conversionService;

	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Cacheable(value = "employee")
	public List<EmployeeDTO> retrieveEmployees(int pageNo, int pageSize) {
		Pageable page = PageRequest.of(pageNo, pageSize);// prep for paginating the results. findAll might return a lot
															// of data later if the table grows so it is better to use
															// pagination.
		List<Employee> employees = employeeRepository.findAll(page).getContent();
		TypeDescriptor sourceType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(Employee.class));
		TypeDescriptor targetType = TypeDescriptor.collection(List.class, TypeDescriptor.valueOf(EmployeeDTO.class));
		return (List<EmployeeDTO>) conversionService.convert(employees, sourceType, targetType);
	}

	@Cacheable(value = "employee", key = "#employeeId")
	public EmployeeDTO getEmployee(Long employeeId) {
		Optional<Employee> optEmp = employeeRepository.findById(employeeId);
		if (!optEmp.isPresent()) {
			throw new EmployeeNotFoundException(employeeId);
		}
		return conversionService.convert(optEmp.get(), EmployeeDTO.class);
	}

	@CachePut(value = "employee", key = "#employee.id")
	public EmployeeDTO saveEmployee(EmployeeDTO employee) {
		Employee result = employeeRepository.save(conversionService.convert(employee, Employee.class));
		return conversionService.convert(result, EmployeeDTO.class);
	}

	@CacheEvict(value = "employee", key = "#employeeId")
	public void deleteEmployee(Long employeeId) {
		try {
			employeeRepository.deleteById(employeeId);
		} catch (EmptyResultDataAccessException e) {
			throw new EmployeeNotFoundException(employeeId);
		}

	}

	@CachePut(value = "employee", key = "#employee.id")
	public EmployeeDTO updateEmployee(EmployeeDTO employee) {
		EmployeeDTO emp = getEmployee(employee.getId());
		Employee result = null;
		if (emp != null) {
			result = employeeRepository.save(conversionService.convert(employee, Employee.class));
		} else {
			throw new EmployeeNotFoundException(employee.getDepartment());
		}
		return conversionService.convert(result, EmployeeDTO.class);

	}

}