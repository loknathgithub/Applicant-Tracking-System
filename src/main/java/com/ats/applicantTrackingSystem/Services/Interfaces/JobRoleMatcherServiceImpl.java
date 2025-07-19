package com.ats.applicantTrackingSystem.Services.Interfaces;

import com.ats.applicantTrackingSystem.Models.JobRoleMatcher;
import java.util.HashMap;

public interface JobRoleMatcherServiceImpl {
    public JobRoleMatcher skillsWeightMapper(String jobID,
                                             String companyName,
                                             Long recruiterID,
                                             HashMap<String,Integer> skillsWeights,
                                             HashMap<String, Integer> optSkillsWeights);
}