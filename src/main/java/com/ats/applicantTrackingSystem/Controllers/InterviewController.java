package com.ats.applicantTrackingSystem.Controllers;

import com.ats.applicantTrackingSystem.DTO.InterviewDTO;
import com.ats.applicantTrackingSystem.Models.Interview;
import com.ats.applicantTrackingSystem.Services.Implementations.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interviews")
public class InterviewController {
    @Autowired
    private InterviewService interviewService;

    @PostMapping
    public ResponseEntity<Interview> create(@RequestBody InterviewDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(interviewService.createInterview(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Interview> update(@PathVariable Long id, @RequestBody InterviewDTO dto) {
        return ResponseEntity.ok(interviewService.updateInterview(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        interviewService.deleteInterview(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Interview>> list(
            @RequestParam(required = false) Long recruiterId,
            @RequestParam(required = false) Long applicationId,
            @RequestParam(required = false) String jobId,
            @RequestParam(required = false) String jobCompanyName
    ) {
        if (jobId != null && jobCompanyName != null && recruiterId != null) {
            return ResponseEntity.ok(interviewService.getInterviewsByJob(jobId, jobCompanyName, recruiterId));
        } else if (recruiterId != null) {
            return ResponseEntity.ok(interviewService.getInterviewsByRecruiter(recruiterId));
        } else if (applicationId != null) {
            return ResponseEntity.ok(interviewService.getInterviewsByCandidate(applicationId));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}

