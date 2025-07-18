package com.ats.applicantTrackingSystem.Controllers;

import com.ats.applicantTrackingSystem.DTO.ApplicationDTO;
import com.ats.applicantTrackingSystem.Models.Application;
import com.ats.applicantTrackingSystem.Models.ENUM.ApplicationStatus;
import com.ats.applicantTrackingSystem.Services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply")
    public ResponseEntity<Application> submitApplication(@RequestBody ApplicationDTO dto) {
        try {
            Application savedApp = applicationService.processApplicationSubmission(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedApp);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(null);  // Consider detailed error handling in production.
        }
    }

    // Get application status
    @GetMapping("/{applicationId}/status")
    public ResponseEntity<ApplicationStatus> getStatus(@PathVariable Long applicationId) {
        ApplicationStatus status = applicationService.getApplicationStatus(applicationId);
        return ResponseEntity.ok(status);
    }

    // Update application status
    @PutMapping("/{applicationId}/status")
    public ResponseEntity<ApplicationStatus> updateStatus(
            @PathVariable Long applicationId,
            @RequestBody Map<String, String> body) {
        ApplicationStatus newStatus = ApplicationStatus.valueOf(body.get("status"));
        ApplicationStatus updated = applicationService.updateApplicationStatus(applicationId, newStatus);
        return ResponseEntity.ok(updated);
    }

}
