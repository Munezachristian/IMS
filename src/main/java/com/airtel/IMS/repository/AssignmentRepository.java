package com.airtel.IMS.repository;

import com.airtel.IMS.entity.Assignment;
import com.airtel.IMS.entity.Device;
import com.airtel.IMS.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByReturnedDateIsNull();
    List<Assignment> findByEmployee(Employee employee);
    List<Assignment> findByDevice(Device device);
    Optional<Assignment> findByDeviceAndReturnedDateIsNull(Device device);
    List<Assignment> findByAssignedDateBetween(LocalDate startDate, LocalDate endDate);
    List<Assignment> findByDeviceOrderByAssignedDateDesc(Device device);
}