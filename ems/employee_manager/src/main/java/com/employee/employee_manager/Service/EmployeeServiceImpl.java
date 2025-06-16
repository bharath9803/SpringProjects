package com.employee.employee_manager.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.employee.employee_manager.Repo.EmployeeRepo;
import com.employee.employee_manager.model.Employee;

@Service
public class EmployeeServiceImpl implements E_Service {

    private final EmployeeRepo employeeRepo;

    public EmployeeServiceImpl(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepo.findById(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepo.deleteById(id);
    }

    @Override
    public List<Employee> findEmployeesByDepartment(String department) {
        return employeeRepo.findByDepartment(department);
    }

    @Override
    public List<String> findDistinctDepartments() {
        return employeeRepo.findDistinctDepartments();
    }
}
