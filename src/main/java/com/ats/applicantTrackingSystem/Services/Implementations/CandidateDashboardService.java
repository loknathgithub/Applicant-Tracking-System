package com.ats.applicantTrackingSystem.Services.Implementations;

import com.ats.applicantTrackingSystem.DTO.CandidateJobDTO;
import com.ats.applicantTrackingSystem.Models.Interview;
import com.ats.applicantTrackingSystem.Repository.InterviewRepository;
import com.ats.applicantTrackingSystem.Models.Application;
import com.ats.applicantTrackingSystem.Repository.ApplicationRepository;
import com.ats.applicantTrackingSystem.Services.Interfaces.CandidateDashboardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CandidateDashboardService implements CandidateDashboardServiceImpl {
    @Autowired
    private ApplicationRepository appRepo;

    @Autowired
    private InterviewRepository interviewRepo;

    public List<CandidateJobDTO> getCandidateDashboard(Long userId) {
        List<Application> applications = appRepo.findByResume_UserId(userId);
        List<Interview> interviews = interviewRepo.findByApplication_Resume_UserId(userId);
        Map<Long, List<Interview>> interviewsByAppId = interviews.stream()
                .collect(Collectors.groupingBy(inv -> inv.getApplication().getApplicationId()));
        List<CandidateJobDTO> result = new ArrayList<>();
        for (Application app : applications) {
            result.add(new CandidateJobDTO(
                    app.getJobPost(),
                    app.getStatus(),
                    interviewsByAppId.getOrDefault(app.getApplicationId(), Collections.emptyList())
            ));
        }
        return null;
    }
}

