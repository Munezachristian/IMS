package com.airtel.IMS.service;

import com.airtel.IMS.entity.Assignment;
import com.airtel.IMS.entity.Device;
import com.airtel.IMS.entity.Employee;
import com.airtel.IMS.repository.AssignmentRepository;
import com.airtel.IMS.repository.DeviceRepository;
import com.airtel.IMS.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AssignmentService {
    
    @Autowired
    private AssignmentRepository assignmentRepository;
    
    @Autowired
    private DeviceRepository deviceRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Transactional
    public Assignment assignDevice(Long deviceId, Long employeeId, String condition, String remarks) {
        Device device = deviceRepository.findById(deviceId)
            .orElseThrow(() -> new RuntimeException("Device not found"));
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        
        Optional<Assignment> activeAssignment = assignmentRepository.findByDeviceAndReturnedDateIsNull(device);
        if (activeAssignment.isPresent()) {
            throw new RuntimeException("Device is already assigned!");
        }
        
        Assignment assignment = new Assignment();
        assignment.setDevice(device);
        assignment.setEmployee(employee);
        assignment.setAssignedDate(LocalDate.now());
        assignment.setConditionOnAssign(condition);
        assignment.setRemarks(remarks);
        assignment.setCreatedAt(LocalDateTime.now());
        
        device.setCurrentStatus(Device.DeviceStatus.ASSIGNED);
        deviceRepository.save(device);
        
        return assignmentRepository.save(assignment);
    }
    
    @Transactional
    public Assignment returnDevice(Long assignmentId, String condition, String remarks) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
            .orElseThrow(() -> new RuntimeException("Assignment not found"));
        
        assignment.setReturnedDate(LocalDate.now());
        assignment.setConditionOnReturn(condition);
        assignment.setRemarks(remarks);
        
        Device device = assignment.getDevice();
        device.setCurrentStatus(Device.DeviceStatus.AVAILABLE);
        deviceRepository.save(device);
        
        return assignmentRepository.save(assignment);
    }
    
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }
    
    public List<Assignment> getActiveAssignments() {
        return assignmentRepository.findByReturnedDateIsNull();
    }
    
    public List<Assignment> getAssignmentsByEmployee(Employee employee) {
        return assignmentRepository.findByEmployee(employee);
    }
    
    public List<Assignment> getAssignmentsByDevice(Device device) {
        return assignmentRepository.findByDevice(device);
    }
    
    public Optional<Assignment> getActiveAssignmentForDevice(Device device) {
        return assignmentRepository.findByDeviceAndReturnedDateIsNull(device);
    }
}
