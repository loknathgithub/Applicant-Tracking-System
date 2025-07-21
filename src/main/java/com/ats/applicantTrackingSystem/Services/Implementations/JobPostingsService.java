package com.ats.applicantTrackingSystem.Services.Implementations;

import com.ats.applicantTrackingSystem.DTO.JobPostingsDTO;
import com.ats.applicantTrackingSystem.ExceptionHandlers.ResourceNotFoundException;
import com.ats.applicantTrackingSystem.Models.CompositePrimaryKeyConfig;
import com.ats.applicantTrackingSystem.Models.JobPostDetails;
import com.ats.applicantTrackingSystem.Repository.JobPostingsRepository;
import com.ats.applicantTrackingSystem.Services.Interfaces.JobPostingsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class JobPostingsService implements JobPostingsServiceImpl {

    @Autowired
    private JobPostingsRepository jobRepo;

    // Deletions of Job Postings
    @Transactional
    public void deleteAllJobs() {
        jobRepo.deleteAll();
    }

    @Transactional
    public void deleteAllJobsByID(List<CompositePrimaryKeyConfig> ids) {
        jobRepo.deleteAllById(ids);
    }

    @Transactional
    public void deleteJobByID(String jobCompanyName, String jobId, Long recruiterId) {
        CompositePrimaryKeyConfig id = new CompositePrimaryKeyConfig(jobId, jobCompanyName, recruiterId);
        jobRepo.deleteById(id);
    }

    // Creation of Job Postings
    @Transactional
    public JobPostDetails addJobPosting(JobPostingsDTO job) {
        System.out.println("Service: " + job);
        JobPostDetails addJob = getJobPostDetails(job); // mapping DTO to JobPostDetails object
        System.out.println("Service: " + addJob);
        return jobRepo.save(addJob);
    }

    public JobPostDetails getJobPostDetails(JobPostingsDTO job) {
        CompositePrimaryKeyConfig jobID = new CompositePrimaryKeyConfig(job.getJobID(), job.getJobCompanyName(), job.getRecruiterID());
        return new JobPostDetails(jobID,
                job.getJobTitle(),
                job.getJobLocation(),
                job.getJobDescription(),
                job.getJobQualification(),
                job.getJobContactMethod(),
                job.getJobWorkType(),
                job.getMinSalary(),
                job.getMaxSalary(),
                job.getPerks(),
                job.getSkills(),
                job.getOptionalSkills(),
                job.getStartDate(),
                job.getDeadline());
    }

    // Fetch Jobs
    @Transactional
    public List<JobPostDetails> fetchAllJobs() {
        return jobRepo.findAll();
    }

    @Transactional
    public Optional<JobPostDetails> fetchJobByID(String jobID, String jobCompanyName, Long recruiterId) {
        CompositePrimaryKeyConfig id = new CompositePrimaryKeyConfig(jobID, jobCompanyName, recruiterId);
        return jobRepo.findById(id);
    }

    @Transactional
    public Optional<JobPostDetails> patchJobById(String jobID, String jobCompanyName, Long recruiterId,
                                                 Map<String, Object> updates) {
        System.out.println("Service:  " + updates);
        CompositePrimaryKeyConfig id = new CompositePrimaryKeyConfig(jobID, jobCompanyName, recruiterId);
        JobPostDetails job = jobRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job Not Found"));

        applyPatchUpdates(job, updates);

        JobPostDetails updatedJob = jobRepo.save(job);
        System.out.println("Service:  " + updatedJob);
        return Optional.of(updatedJob);
    }

    @Transactional
    public JobPostDetails updateJob(String jobID, String jobCompanyName, Long recruiterId, JobPostDetails updatedJobDetails) {
        CompositePrimaryKeyConfig id = new CompositePrimaryKeyConfig(jobID, jobCompanyName, recruiterId);

        JobPostDetails existingJob = jobRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job Not Found"));

        // Overwrite all fields except the ID
        existingJob.setJobTitle(updatedJobDetails.getJobTitle());
        existingJob.setJobLocation(updatedJobDetails.getJobLocation());
        existingJob.setJobDescription(updatedJobDetails.getJobDescription());
        existingJob.setJobQualification(updatedJobDetails.getJobQualification());
        existingJob.setJobContactMethod(updatedJobDetails.getJobContactMethod());
        existingJob.setJobWorkType(updatedJobDetails.getJobWorkType());
        existingJob.setSkills(updatedJobDetails.getSkills());

        // Optional fields
        existingJob.setOptionalSkills(updatedJobDetails.getOptionalSkills());
        existingJob.setMinSalary(updatedJobDetails.getMinSalary());
        existingJob.setMaxSalary(updatedJobDetails.getMaxSalary());
        existingJob.setPerks(updatedJobDetails.getPerks());
        existingJob.setStartDate(updatedJobDetails.getStartDate());
        existingJob.setDeadline(updatedJobDetails.getDeadline());

        return jobRepo.save(existingJob);
    }

    // Used by patchJobById - safely apply partial updates
    public void applyPatchUpdates(Object target, Map<String, Object> updates) {
        updates.forEach((fieldName, value) -> {
            // Skip updating primary key fields or nulls
            if (value == null || "id".equals(fieldName) || "jobID".equals(fieldName)
                    || "jobCompanyName".equals(fieldName) || "recruiterID".equals(fieldName)) {
                return;
            }
            Field field = ReflectionUtils.findField(target.getClass(), fieldName);
            if (field != null) {
                field.setAccessible(true);
                if (field.getType().isArray() && value instanceof List) {
                    List<?> list = (List<?>) value;
                    Object array = java.lang.reflect.Array.newInstance(field.getType().getComponentType(), list.size());
                    for (int i = 0; i < list.size(); i++) {
                        java.lang.reflect.Array.set(array, i, list.get(i));
                    }
                    ReflectionUtils.setField(field, target, array);
                } else {
                    ReflectionUtils.setField(field, target, value);
                }
            }
        });
    }

    // Filter and Search Logic (Optional) ...
}
