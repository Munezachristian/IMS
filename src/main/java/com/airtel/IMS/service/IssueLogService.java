package com.airtel.IMS.service;

import com.airtel.IMS.entity.IssueLog;
import com.airtel.IMS.entity.Device;
import com.airtel.IMS.entity.Employee;
import com.airtel.IMS.repository.IssueLogRepository;
import com.airtel.IMS.repository.DeviceRepository;
import com.airtel.IMS.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class IssueLogService {
    
    @Autowired
    private IssueLogRepository issueLogRepository;
    
    @Autowired
    private DeviceRepository deviceRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Transactional
    public IssueLog reportIssue(Long deviceId, Long reportedByEmployeeId, String issueDescription) {
        Device device = deviceRepository.findById(deviceId)
            .orElseThrow(() -> new RuntimeException("Device not found"));
        Employee reportedBy = employeeRepository.findById(reportedByEmployeeId)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        
        IssueLog issueLog = new IssueLog();
        issueLog.setDevice(device);
        issueLog.setReportedBy(reportedBy);
        issueLog.setIssueDescription(issueDescription);
        issueLog.setReportedDate(LocalDate.now());
        issueLog.setResolutionStatus("PENDING");
        issueLog.setCreatedAt(LocalDateTime.now());
        
        device.setCurrentStatus(Device.DeviceStatus.UNDER_MAINTENANCE);
        deviceRepository.save(device);
        
        return issueLogRepository.save(issueLog);
    }
    
    @Transactional
    public IssueLog updateIssueStatus(Long issueId, String status, String resolutionNotes) {
        IssueLog issueLog = issueLogRepository.findById(issueId)
            .orElseThrow(() -> new RuntimeException("Issue not found"));
        
        issueLog.setResolutionStatus(status);
        issueLog.setResolutionNotes(resolutionNotes);
        
        if ("RESOLVED".equals(status) || "CLOSED".equals(status)) {
            issueLog.setResolvedDate(LocalDate.now());
            Device device = issueLog.getDevice();
            device.setCurrentStatus(Device.DeviceStatus.AVAILABLE);
            deviceRepository.save(device);
        }
        
        return issueLogRepository.save(issueLog);
    }
    
    public List<IssueLog> getAllIssues() {
        return issueLogRepository.findAll();
    }
    
    public List<IssueLog> getIssuesByDevice(Device device) {
        return issueLogRepository.findByDevice(device);
    }
    
    public List<IssueLog> getIssuesByStatus(String status) {
        return issueLogRepository.findByResolutionStatus(status);
    }
    
    public List<IssueLog> getPendingIssues() {
        return issueLogRepository.findByResolutionStatus("PENDING");
    }
}