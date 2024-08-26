package com.employee.employee_manager.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.employee.employee_manager.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    @Query("SELECT DISTINCT e.department FROM Employee e")
    List<String> findDistinctDepartments();

    // Custom query method to find employees by department
    List<Employee> findByDepartment(String department);
}
