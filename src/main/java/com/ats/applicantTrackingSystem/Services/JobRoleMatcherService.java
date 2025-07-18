package com.ats.applicantTrackingSystem.Services;

import com.ats.applicantTrackingSystem.ExceptionHandlers.ResourceNotFoundException;
import com.ats.applicantTrackingSystem.Models.CompositePrimaryKeyConfig;
import com.ats.applicantTrackingSystem.Models.JobPostDetails;
import com.ats.applicantTrackingSystem.Models.JobRoleMatcher;
import com.ats.applicantTrackingSystem.Repository.JobPostingsRepository;
import com.ats.applicantTrackingSystem.Repository.JobRoleMatcherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;

@Service
public class JobRoleMatcherService {
    @Autowired
    private JobRoleMatcherRepository matchRepo;

    @Autowired
    private JobPostingsRepository jobRepo;

    @Transactional
    public JobRoleMatcher skillsWeightMapper(String jobID,
                                             String companyName,
                                             Long recruiterID,
                                             HashMap<String,Integer> skillsWeights,
                                             HashMap<String, Integer> optSkillsWeights){

        CompositePrimaryKeyConfig id = new CompositePrimaryKeyConfig(jobID, companyName, recruiterID);
        JobPostDetails job = jobRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job Not Found"));

        JobRoleMatcher weightedJob = new JobRoleMatcher(id,
                                                        job.getJobTitle() ,
                                                        job.getJobLocation(),
                                                        job.getJobDescription(),
                                                        job.getJobQualification(),
                                                        skillsWeights,
                                                        optSkillsWeights);

        return matchRepo.save(weightedJob);
    }
}