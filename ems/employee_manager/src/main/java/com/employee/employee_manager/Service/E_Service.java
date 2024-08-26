package com.employee.employee_manager.Service;

import java.util.List;
import java.util.Optional;

import com.employee.employee_manager.model.Employee;

public interface E_Service {
    List<Employee> getAllEmployees();
    
    Optional<Employee> getEmployeeById(Long id);
    
    Employee saveEmployee(Employee employee);
    
    void deleteEmployee(Long id);
    
    List<Employee> findEmployeesByDepartment(String department);
    
    List<String> findDistinctDepartments();

}
