package com.spring.emsbackend.service.impl;

import com.spring.emsbackend.dto.EmployeeDto;
import com.spring.emsbackend.entity.Employee;
import com.spring.emsbackend.exception.ResourceNotFoundException;
import com.spring.emsbackend.mapper.EmployeeMapper;
import com.spring.emsbackend.repository.EmployeeRepository;
import com.spring.emsbackend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow( () -> new ResourceNotFoundException("Employee does not exist with the id: " + employeeId) );

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeesDto = new ArrayList<>();
        for(Employee emp : employees){
            employeesDto.add(EmployeeMapper.mapToEmployeeDto(emp));
        }
        return employeesDto;
//        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
//                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
       Employee employee = employeeRepository.findById(employeeId).orElseThrow(
               () ->  new ResourceNotFoundException("Employee does not exist.")
       );
       employee.setFirstName(updatedEmployee.getFirstName());
       employee.setEmail(updatedEmployee.getEmail());
       employee.setLastName(updatedEmployee.getLastName());
       Employee updatedEmpObj = employeeRepository.save(employee);
       return EmployeeMapper.mapToEmployeeDto(updatedEmpObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                () ->  new ResourceNotFoundException("Employee not found.")
        );

        employeeRepository.deleteById(employeeId);
    }
}
