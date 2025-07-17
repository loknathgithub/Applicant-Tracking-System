package com.ats.applicantTrackingSystem.Services;

import com.ats.applicantTrackingSystem.ExceptionHandlers.ResourceNotFoundException;
import com.ats.applicantTrackingSystem.Models.*;
import com.ats.applicantTrackingSystem.Repository.JobRoleMatcherRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class WeightedSearchAlgorithm {

    @Autowired
    private JobRoleMatcherRepository matchRepo;

    public void fetchDetails(Application application, JobPostDetails job){   // This function will be in-action when candidate applied for a job
        ResumeDetails resumeInfo = application.getResume();
        Set<String> candidateSkills = resumeInfo.getSkills();

        CompositePrimaryKeyConfig id = job.getId();
        JobRoleMatcher fetchedJob = matchRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Job Not Found"));
        Map<String, Integer> skills = fetchedJob.getSkills();
        Map<String, Integer> optSkills = fetchedJob.getOptionalSkills();

        Map<String, Integer> requiredSkills = new HashMap<>(skills); // concatenated
        requiredSkills.putAll(optSkills);

        int score = matchSkills(requiredSkills, candidateSkills);   // getting score
        application.setScore(score);                                // Update the score
    }

    // write the logic to compare skills required and have
    // implement the semantic search algo
    public int matchSkills(Map<String, Integer> requiredSkills, Set<String> candidateSkills){
        int score = 0;

        for(String skill: candidateSkills){
            if (requiredSkills.containsKey(skill))
                score += requiredSkills.get(skill);
        }

        return score;
    }
}