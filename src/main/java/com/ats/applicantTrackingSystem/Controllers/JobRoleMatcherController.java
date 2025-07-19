package com.ats.applicantTrackingSystem.Controllers;

import com.ats.applicantTrackingSystem.Models.JobRoleMatcher;
import com.ats.applicantTrackingSystem.Models.ResumeTemplate.Resume;
import com.ats.applicantTrackingSystem.Services.Implementations.JobRoleMatcherService;
import com.ats.applicantTrackingSystem.Algorithms.PDFMatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class JobRoleMatcherController {
    @Autowired
    private JobRoleMatcherService jobRoleService;

    @Autowired
    private PDFMatcherService pdfMatcherService;

    @PostMapping("/add/weights")    // maybe replace: id & companyName to path variable
    public ResponseEntity<JobRoleMatcher> addWeights(@RequestBody String id,
                                                     @RequestBody String companyName,
                                                     @RequestBody Long recruiterID,
                                                     @RequestBody HashMap<String, Integer> skillsWeights,
                                                     @RequestBody HashMap<String, Integer> optSkillsWeights){
        try {
            JobRoleMatcher weightedJob = jobRoleService.skillsWeightMapper(id, companyName, recruiterID, skillsWeights, optSkillsWeights);
            return ResponseEntity.status(HttpStatus.OK).body(weightedJob);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/pdftest")
    public ResponseEntity<Resume> pdfParsing(@RequestParam String pdfURL){    //change URL to take origin PDF or alternative
        try{
            return ResponseEntity.status(HttpStatus.OK).body(pdfMatcherService.parseResume(pdfURL));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}