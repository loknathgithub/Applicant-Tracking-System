package com.ats.applicantTrackingSystem.Controllers;

import com.ats.applicantTrackingSystem.DTO.JobPostingsDTO;
import com.ats.applicantTrackingSystem.ExceptionHandlers.ResourceNotFoundException;
import com.ats.applicantTrackingSystem.Models.CompositePrimaryKeyConfig;
import com.ats.applicantTrackingSystem.Models.JobPostDetails;
import com.ats.applicantTrackingSystem.Services.JobPostingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/jobs")
public class JobPostingsController {

    @Autowired
    private JobPostingsService jobService;

    @PostMapping("/add")
    public ResponseEntity<JobPostDetails> addJob(@RequestBody JobPostingsDTO job){
        try {
            JobPostDetails fetchedJob = jobService.addJobPosting(job);
            return ResponseEntity.status(HttpStatus.CREATED).body(fetchedJob);
        } catch (Exception e) {
            throw new RuntimeException("Error Occurred: " + e);
        }
    }

    @GetMapping("/get/allJobPosts")
    public ResponseEntity<List<JobPostDetails>> fetchAllJobs(){
        try {
            List<JobPostDetails> allJobs = jobService.fetchAllJobs();
            return ResponseEntity.status(HttpStatus.OK).body(allJobs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllJobs(){
        try {
            jobService.deleteAllJobs();
            return ResponseEntity.status(HttpStatus.OK).body("All Jobs are Deleted");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<List<CompositePrimaryKeyConfig>> deleteJobsById(@RequestBody List<CompositePrimaryKeyConfig> ids){
        try {
            jobService.deleteAllJobsByID(ids);
            return ResponseEntity.status(HttpStatus.OK).body(ids);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{companyName}/{jobId}/{recruiterId}")
    public ResponseEntity<String> deleteJobById(
            @PathVariable String companyName,
            @PathVariable String jobId,
            @PathVariable Long recruiterId
    ) {
        try {
            jobService.deleteJobByID(companyName, jobId, recruiterId);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Job Deleted: " + jobId + ", company: " + companyName + ", recruiter: " + recruiterId);
        } catch(Exception e) {
            throw new RuntimeException("Error took place:" + e);
        }
    }

    @GetMapping("/get/{companyName}/{jobId}/{recruiterId}")
    public ResponseEntity<Optional<JobPostDetails>> fetchJobById(
            @PathVariable String companyName,
            @PathVariable String jobId,
            @PathVariable Long recruiterId
    ) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(jobService.fetchJobByID(jobId, companyName, recruiterId));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", e);
        }
    }

    @PutMapping("/update/{companyName}/{jobId}/{recruiterId}")
    public ResponseEntity<JobPostDetails> updateJobPost(
            @PathVariable String companyName,
            @PathVariable String jobId,
            @PathVariable Long recruiterId,
            @RequestBody JobPostDetails updates
    ) {
        try {
            JobPostDetails updatedJob = jobService.updateJob(jobId, companyName, recruiterId, updates);
            return ResponseEntity.status(HttpStatus.OK).body(updatedJob);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", e);
        }
    }

    @PatchMapping("/updateDetails/{companyName}/{jobId}/{recruiterId}")
    public ResponseEntity<JobPostDetails> updateJobDetails(
            @PathVariable String companyName,
            @PathVariable String jobId,
            @PathVariable Long recruiterId,
            @RequestBody Map<String, Object> updates
    ) {
        try {
            Optional<JobPostDetails> updatedJob = jobService.patchJobById(jobId, companyName, recruiterId, updates);
            return updatedJob
                    .map(job -> ResponseEntity.status(HttpStatus.OK).body(job))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", e);
        }
    }

    // Filter and Search Logic can be added here...
}
