package com.ats.applicantTrackingSystem.Controllers;

import com.ats.applicantTrackingSystem.Models.JobRoleMatcher;
import com.ats.applicantTrackingSystem.Services.JobRoleMatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

@RestController
public class JobRoleMatcherController {
    @Autowired
    private JobRoleMatcherService jobRoleService;

    @PostMapping("/add/weights")    // maybe replace: id & companyName to path variable
    public ResponseEntity<JobRoleMatcher> addWeights(@RequestBody String id,
                                                     @RequestBody String companyName,
                                                     @RequestBody HashMap<String, Integer> skillsWeights,
                                                     @RequestBody HashMap<String, Integer> optSkillsWeights){
        try {
            JobRoleMatcher weightedJob = jobRoleService.skillsWeightMapper(id, companyName, skillsWeights, optSkillsWeights);
            return ResponseEntity.status(HttpStatus.OK).body(weightedJob);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}