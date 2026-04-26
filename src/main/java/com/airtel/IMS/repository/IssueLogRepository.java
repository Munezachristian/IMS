package com.airtel.IMS.repository;

import com.airtel.IMS.entity.IssueLog;
import com.airtel.IMS.entity.Device;
import com.airtel.IMS.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IssueLogRepository extends JpaRepository<IssueLog, Long> {
    List<IssueLog> findByDevice(Device device);
    List<IssueLog> findByReportedBy(Employee employee);
    List<IssueLog> findByResolutionStatus(String status);
    List<IssueLog> findByReportedDateBetween(LocalDate startDate, LocalDate endDate);
    List<IssueLog> findByIssueDescriptionContaining(String keyword);
}