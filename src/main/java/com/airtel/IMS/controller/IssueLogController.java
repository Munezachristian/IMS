package com.airtel.IMS.controller;

import com.airtel.IMS.entity.IssueLog;
import com.airtel.IMS.service.IssueLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/issues")
@CrossOrigin(origins = "*")
public class IssueLogController {
    
    @Autowired
    private IssueLogService issueLogService;
    
    @PostMapping("/report")
    public ResponseEntity<?> reportIssue(@RequestParam Long deviceId,
                                         @RequestParam Long reportedByEmployeeId,
                                         @RequestParam String issueDescription) {
        try {
            return new ResponseEntity<>(issueLogService.reportIssue(deviceId, reportedByEmployeeId, issueDescription), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PutMapping("/{issueId}/status")
    public ResponseEntity<?> updateIssueStatus(@PathVariable Long issueId,
                                               @RequestParam String status,
                                               @RequestParam(required = false) String resolutionNotes) {
        try {
            return new ResponseEntity<>(issueLogService.updateIssueStatus(issueId, status, resolutionNotes), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping
    public ResponseEntity<List<IssueLog>> getAllIssues() {
        return new ResponseEntity<>(issueLogService.getAllIssues(), HttpStatus.OK);
    }
    
    @GetMapping("/pending")
    public ResponseEntity<List<IssueLog>> getPendingIssues() {
        return new ResponseEntity<>(issueLogService.getPendingIssues(), HttpStatus.OK);
    }
}