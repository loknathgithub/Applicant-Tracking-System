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
                                                     @RequestBody String jobCompanyName,
                                                     @RequestBody Long recruiterID,
                                                     @RequestBody HashMap<String, Integer> skillsWeights,
                                                     @RequestBody HashMap<String, Integer> optSkillsWeights){
        try {
            JobRoleMatcher weightedJob = jobRoleService.skillsWeightMapper(id, jobCompanyName, recruiterID, skillsWeights, optSkillsWeights);
            return ResponseEntity.status(HttpStatus.OK).body(weightedJob);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/pdftest")
    public ResponseEntity<Resume> pdfParsing(@RequestParam String pdfURL){
        try{
            Resume resume;
            if (pdfURL.startsWith("http://") || pdfURL.startsWith("https://")) {
                resume = pdfMatcherService.parseResumeFromUrl(pdfURL);
            } else {
                resume = pdfMatcherService.parseResume(pdfURL);
            }
            return ResponseEntity.status(HttpStatus.OK).body(resume);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}