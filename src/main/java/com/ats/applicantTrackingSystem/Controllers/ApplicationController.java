package com.ats.applicantTrackingSystem.Controllers;

import com.ats.applicantTrackingSystem.DTO.ApplicationDTO;
import com.ats.applicantTrackingSystem.Models.Application;
import com.ats.applicantTrackingSystem.Services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
