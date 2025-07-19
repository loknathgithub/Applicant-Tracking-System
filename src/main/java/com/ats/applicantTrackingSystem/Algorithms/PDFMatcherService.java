package com.ats.applicantTrackingSystem.Algorithms;

import com.ats.applicantTrackingSystem.Models.ResumeTemplate.Resume;
import com.ats.applicantTrackingSystem.Models.ResumeTemplate.Education;
import com.ats.applicantTrackingSystem.Models.ResumeTemplate.Experience;
import com.ats.applicantTrackingSystem.Models.ResumeTemplate.Certification;
import com.ats.applicantTrackingSystem.Models.ResumeTemplate.Project;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.*;

@Service
public class PDFMatcherService {

    public Resume parseResume(String pdfPath) throws IOException {
        String text = extractTextFromPDF(pdfPath);
        Resume resume = new Resume();

        resume.setName(extractSingleLine(text, "(?i)name[:\\s]+(.+)"));
        resume.setEmail(extractSingleLine(text, "(?i)email[:\\s]+([\\w.%-]+@[\\w.-]+)"));
        resume.setPhone(extractSingleLine(text, "(?i)phone[:\\s]+([+\\d\\-()\\s]+)"));
        resume.setLocation(extractSingleLine(text, "(?i)location[:\\s]+(.+)"));

        //Section Regex
        resume.setSummary(extractSection(text, "(?i)summary[:\\s]*", "(?i)(skills|education|experience|certifications|projects)[:\\s]*"));
        //Skills Regex
        resume.setSkills(extractList(text, "(?i)skills[:\\s]*([\\s\\S]+?)(?=education:|experience:|certifications:|projects:|$)"));
        //Education Regex
        resume.setEducation(extractEducationList(text));
        //Experience Regex
        resume.setExperience(extractExperienceList(text));
        //Certification Regex
        resume.setCertifications(extractCertificationList(text));
        //Project Regex
        resume.setProjects(extractProjectList(text));

        System.out.println("In PDFMatcherService: \nHere are parsed details:\n"+resume);
        return resume;
    }

    private String extractTextFromPDF(String pdfPath) throws IOException {
        try (PDDocument document = Loader.loadPDF(new File(pdfPath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

    //Lines
    private String extractSingleLine(String text, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(text);
        return matcher.find() ? matcher.group(1).trim() : null;
    }

    //Sections
    private String extractSection(String text, String startRegex, String endRegex) {
        Pattern pattern = Pattern.compile(startRegex + "([\\s\\S]+?)" + endRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(1).trim() : null;
    }

    //Lists
    private List<String> extractList(String text, String regex) {
        Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(text);
        if (matcher.find()) {
            String[] items = matcher.group(1).split("[,\\n]");
            List<String> list = new ArrayList<>();
            for (String item : items) {
                String trimmed = item.trim();
                if (!trimmed.isEmpty()) list.add(trimmed);
            }
            return list;
        }
        return Collections.emptyList();
    }

    //Education
    private static List<Education> extractEducationList(String text) {
        List<Education> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(
                "(?i)education[:\\s]*([\\s\\S]+?)(?=experience:|certifications:|projects:|$)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String eduBlock = matcher.group(1);
            Pattern eduPattern = Pattern.compile(
                    "(B\\.Tech|M\\.Tech|BSc|MSc|BE|ME|Bachelor|Master)[^\\n]*", Pattern.CASE_INSENSITIVE);
            Matcher eduMatcher = eduPattern.matcher(eduBlock);
            while (eduMatcher.find()) {
                String line = eduMatcher.group();
                Education edu = new Education();
                // Example: "B.Tech in Computer Science, XYZ University, 2016-2020"
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    edu.setDegree(parts[0].replaceAll(" in .*", "").trim());
                    edu.setField(parts[0].replaceAll(".* in ", "").trim());
                    edu.setInstitution(parts[1].trim());
                    String[] years = parts[2].replaceAll("[^\\d\\-]", "").split("-");
                    if (years.length == 2) {
                        edu.setStartYear(years[0]);
                        edu.setEndYear(years[1]);
                    }
                }
                list.add(edu);
            }
        }
        return list;
    }

    // Experience: Extracts all experience blocks
    private static List<Experience> extractExperienceList(String text) {
        List<Experience> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(
                "(?i)experience[:\\s]*([\\s\\S]+?)(?=certifications:|projects:|$)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String expBlock = matcher.group(1);
            Pattern expPattern = Pattern.compile(
                    "([A-Za-z ]+) at ([A-Za-z0-9 ]+), ([\\d-]+) to ([\\w-]+)[\\n\\r]+(.+?)(?=\\n|$)",
                    Pattern.CASE_INSENSITIVE);
            Matcher expMatcher = expPattern.matcher(expBlock);
            while (expMatcher.find()) {
                Experience exp = new Experience();
                exp.setJobTitle(expMatcher.group(1).trim());
                exp.setCompany(expMatcher.group(2).trim());
                exp.setStartDate(expMatcher.group(3).trim());
                exp.setEndDate(expMatcher.group(4).trim());
                exp.setDescription(expMatcher.group(5).trim());
                list.add(exp);
            }
        }
        return list;
    }

    // Certifications
    private static List<Certification> extractCertificationList(String text) {
        List<Certification> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(
                "(?i)certifications[:\\s]*([\\s\\S]+?)(?=projects:|$)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String certBlock = matcher.group(1);
            Pattern certPattern = Pattern.compile(
                    "([\\w .]+), ([\\w .]+), (\\d{4}-\\d{2})", Pattern.CASE_INSENSITIVE);
            Matcher certMatcher = certPattern.matcher(certBlock);
            while (certMatcher.find()) {
                Certification cert = new Certification();
                cert.setName(certMatcher.group(1).trim());
                cert.setIssuer(certMatcher.group(2).trim());
                cert.setIssueDate(certMatcher.group(3).trim());
                list.add(cert);
            }
        }
        return list;
    }

    // Projects
    private static List<Project> extractProjectList(String text) {
        List<Project> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(
                "(?i)projects[:\\s]*([\\s\\S]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            String projBlock = matcher.group(1);
            Pattern projPattern = Pattern.compile(
                    "([\\w .]+)\\s*\\(([^)]+)\\):\\s*(.+)", Pattern.CASE_INSENSITIVE);
            Matcher projMatcher = projPattern.matcher(projBlock);
            while (projMatcher.find()) {
                Project proj = new Project();
                proj.setTitle(projMatcher.group(1).trim());
                proj.setTechnologies(Arrays.asList(projMatcher.group(2).split(",")));
                proj.setDescription(projMatcher.group(3).trim());
                list.add(proj);
            }
        }
        return list;
    }


    // (rest of extraction methods, non-static, as above)
}
