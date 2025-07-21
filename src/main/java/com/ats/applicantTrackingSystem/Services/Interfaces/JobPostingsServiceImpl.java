package com.ats.applicantTrackingSystem.Services.Interfaces;

import com.ats.applicantTrackingSystem.DTO.JobPostingsDTO;
import com.ats.applicantTrackingSystem.Models.CompositePrimaryKeyConfig;
import com.ats.applicantTrackingSystem.Models.JobPostDetails;

import java.util.List;
import java.util.Map;
import java.util.Optional;
public interface JobPostingsServiceImpl {
    
    // Deletions of Job Postings
    public void deleteAllJobs();
    public void deleteAllJobsByID(List<CompositePrimaryKeyConfig> ids);
    public void deleteJobByID(String jobCompanyName, String jobId, Long recruiterId);

    // Creation of Job Postings
    public JobPostDetails addJobPosting(JobPostingsDTO job);
    public JobPostDetails getJobPostDetails(JobPostingsDTO job);

    // Fetch Jobs
    
    public List<JobPostDetails> fetchAllJobs();
    public Optional<JobPostDetails> fetchJobByID(String jobID, String jobCompanyName, Long recruiterId);
    public Optional<JobPostDetails> patchJobById(String jobID, String jobCompanyName, Long recruiterId,
                                                 Map<String, Object> updates);
    public JobPostDetails updateJob(String jobID, String jobCompanyName, Long recruiterId, JobPostDetails updatedJobDetails);

    // Used by patchJobById - safely apply partial updates
    public void applyPatchUpdates(Object target, Map<String, Object> updates);

    // Filter and Search Logic (Optional) ...
}
