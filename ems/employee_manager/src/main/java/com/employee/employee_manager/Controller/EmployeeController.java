package com.employee.employee_manager.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RestController;

import com.employee.employee_manager.Service.E_Service;
import com.employee.employee_manager.model.Employee;


@RestController
@RequestMapping("/employees")
public class EmployeeController {
 private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final E_Service employeeService;

    @Autowired
    public EmployeeController(E_Service employeeService) {
        this.employeeService = employeeService;
    }

    // Get all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        logger.info("Fetching all employees");

        List<Employee> employees = employeeService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable Long id) {
        logger.info("Fetching employee with ID: {}", id);

        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.isPresent() ? new ResponseEntity<>(employee, HttpStatus.OK)
                                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Add a new employee
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        logger.info("Adding new employee: {}", employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // Update an existing employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        logger.info("Updating employee with ID: {}", id);

        if (!employeeService.getEmployeeById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employee.setId(id);
        Employee updatedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    // Delete an employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        logger.info("Deleting employee with ID: {}", id);

        if (!employeeService.getEmployeeById(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Get employees by department
    @GetMapping("/department/{department}")
    public ResponseEntity<List<Employee>> getEmployeesByDepartment(@PathVariable String department) {
        logger.info("Fetching employees in department: {}", department);

        List<Employee> employees = employeeService.findEmployeesByDepartment(department);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    // Get distinct departments
    @GetMapping("/departments")
    public ResponseEntity<List<String>> getDistinctDepartments() {
        List<String> departments = employeeService.findDistinctDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }
}
