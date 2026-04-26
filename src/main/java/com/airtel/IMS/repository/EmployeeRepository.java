package com.airtel.IMS.repository;

import com.airtel.IMS.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmployeeCode(String employeeCode);
    List<Employee> findByDepartment(String department);
    List<Employee> findByNameContaining(String name);
    boolean existsByEmployeeCode(String employeeCode);
}