package com.airtel.IMS.controller;

import com.airtel.IMS.entity.Assignment;
import com.airtel.IMS.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@CrossOrigin(origins = "*")
public class AssignmentController {
    
    @Autowired
    private AssignmentService assignmentService;
    
    @PostMapping("/assign")
    public ResponseEntity<?> assignDevice(@RequestParam Long deviceId, 
                                          @RequestParam Long employeeId,
                                          @RequestParam String condition,
                                          @RequestParam(required = false) String remarks) {
        try {
            return new ResponseEntity<>(assignmentService.assignDevice(deviceId, employeeId, condition, remarks), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/return/{assignmentId}")
    public ResponseEntity<?> returnDevice(@PathVariable Long assignmentId,
                                          @RequestParam String condition,
                                          @RequestParam(required = false) String remarks) {
        try {
            return new ResponseEntity<>(assignmentService.returnDevice(assignmentId, condition, remarks), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Assignment>> getAllAssignments() {
        return new ResponseEntity<>(assignmentService.getAllAssignments(), HttpStatus.OK);
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<Assignment>> getActiveAssignments() {
        return new ResponseEntity<>(assignmentService.getActiveAssignments(), HttpStatus.OK);
    }
}