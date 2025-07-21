package com.ats.applicantTrackingSystem.Algorithms;

import com.ats.applicantTrackingSystem.Models.ResumeTemplate.Resume;
import com.ats.applicantTrackingSystem.Models.ResumeTemplate.Education;
import com.ats.applicantTrackingSystem.Models.ResumeTemplate.Experience;
import com.ats.applicantTrackingSystem.Models.ResumeTemplate.Certification;
import com.ats.applicantTrackingSystem.Models.ResumeTemplate.Project;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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

        // Section Regex
        resume.setSummary(extractSection(text, "(?i)summary[:\\s]*", "(?i)(skills|education|experience|certifications|projects)[:\\s]*"));
        // Skills Regex
        resume.setSkills(extractList(text, "(?i)skills[:\\s]*([\\s\\S]+?)(?=education:|experience:|certifications:|projects:|$)"));
        // Education Regex
        resume.setEducation(extractEducationList(text));
        // Experience Regex
        resume.setExperience(extractExperienceList(text));
        // Certification Regex
        resume.setCertifications(extractCertificationList(text));
        // Project Regex
        resume.setProjects(extractProjectList(text));

        System.out.println("In PDFMatcherService: \nHere are parsed details:\n" + resume);
        return resume;
    }

    public Resume parseResumeFromUrl(String pdfUrl) throws IOException {
        String text = extractTextFromPDF(new URL(pdfUrl));
        return parseResumeFromText(text);
    }

    // This method parses the text (refactored; see below)
    private Resume parseResumeFromText(String text) {
        Resume resume = new Resume();
        resume.setName(extractSingleLine(text, "(?i)name[:\\s]+(.+)"));
        resume.setEmail(extractSingleLine(text, "(?i)email[:\\s]+([\\w.%-]+@[\\w.-]+)"));
        resume.setPhone(extractSingleLine(text, "(?i)phone[:\\s]+([+\\d\\-()\\s]+)"));
        resume.setLocation(extractSingleLine(text, "(?i)location[:\\s]+(.+)"));
        resume.setSummary(extractSection(text, "(?i)summary[:\\s]*", "(?i)(skills|education|experience|certifications|projects)[:\\s]*"));
        resume.setSkills(extractList(text, "(?i)skills[:\\s]*([\\s\\S]+?)(?=education:|experience:|certifications:|projects:|$)"));
        resume.setEducation(extractEducationList(text));
        resume.setExperience(extractExperienceList(text));
        resume.setCertifications(extractCertificationList(text));
        resume.setProjects(extractProjectList(text));
        return resume;
    }

    // New extractTextFromPDF for URL
    private String extractTextFromPDF(URL pdfUrl) throws IOException {
        try (InputStream in = pdfUrl.openStream();
             PDDocument document = Loader.loadPDF((RandomAccessRead) in)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }


    // Summary
    private String extractSummary(String text) {
        Pattern pattern = Pattern.compile("Summary\\s+([\\s\\S]+)$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find() ? matcher.group(1).replaceAll("[\\n\\r]+", " ").trim() : null;
    }



    // Name (First non-empty line)
    private String extractName(String text) {
        String[] lines = text.split("\\r?\\n");
        for (String line : lines) {
            if (line.trim().matches("[A-Za-z ]{2,}")) { // basic check: non-empty and mostly letters
                return line.trim();
            }
        }
        return null;
    }

    // Phone and Email (look for patterns anywhere)
    private String extractPhone(String text) {
        Matcher matcher = Pattern.compile("(\\+\\d{10,15}|\\d{10})").matcher(text);
        return matcher.find() ? matcher.group(1).trim() : null;
    }
    private String extractEmail(String text) {
        Matcher matcher = Pattern.compile("([\\w.%-]+@[\\w.-]+)").matcher(text);
        return matcher.find() ? matcher.group(1).trim() : null;
    }

    // LinkedIn, GitHub
    private String extractLinkedIn(String text) {
        Matcher matcher = Pattern.compile("(https?://www.linkedin.com/in/\\S+)").matcher(text);
        return matcher.find() ? matcher.group(1).trim() : null;
    }
    private String extractGitHub(String text) {
        Matcher matcher = Pattern.compile("(https?://github.com/\\S+)").matcher(text);
        return matcher.find() ? matcher.group(1).trim() : null;
    }


    private List<String> extractTechnologies(String text) {
        Matcher matcher = Pattern.compile("Technologies:\\s*([\\w, .-]+)").matcher(text);
        if (matcher.find()) {
            return Arrays.stream(matcher.group(1).split(","))
                    .map(String::trim).filter(s -> !s.isEmpty()).toList();
        }
        return List.of();
    }

    private List<String> extractFrameworks(String text) {
        Matcher matcher = Pattern.compile("Framework/Tools:\\s*([\\w.,\\s-]+)").matcher(text);
        if (matcher.find()) {
            return Arrays.stream(matcher.group(1).split(","))
                    .map(String::trim).filter(s -> !s.isEmpty()).toList();
        }
        return List.of();
    }

    private List<Project> extractProjects(String text) {
        List<Project> projects = new ArrayList<>();
        Pattern sectionPattern = Pattern.compile("Projects\\s+(.*?)\\s+Education", Pattern.DOTALL | Pattern.CASE_INSENSITIVE);
        Matcher sectionMatcher = sectionPattern.matcher(text);
        if (sectionMatcher.find()) {
            String projectsBlock = sectionMatcher.group(1);
            Pattern projectPattern = Pattern.compile("([\\w\\- ().]+)(?:\\|\\| Link:\\s*(https?://\\S+))?\\s*([\\s\\S]+?)(?=(?:\\n\\S|$))", Pattern.CASE_INSENSITIVE);
            Matcher matcher = projectPattern.matcher(projectsBlock);
            while (matcher.find()) {
                Project proj = new Project();
                proj.setTitle(matcher.group(1).trim());
                List<String> links = new ArrayList<>();
                if (matcher.group(2) != null) links.add(matcher.group(2));
                proj.setTechnologies(List.of()); // Could improve by scraping for stack mentions inside desc
                // Clean description by stripping bullets and newlines:
                String desc = matcher.group(3).replaceAll("^[\\s\\u2022\\-•]+", "").replaceAll("[\\r\\n]+", " ").trim();
                proj.setDescription(desc);
                projects.add(proj);
            }
        }
        return projects;
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
//    private static List<Education> extractEducationList(String text) {
//        List<Education> list = new ArrayList<>();
//        Pattern pattern = Pattern.compile(
//                "(?i)education[:\\s]*([\\s\\S]+?)(?=experience:|certifications:|projects:|$)", Pattern.CASE_INSENSITIVE);
//        Matcher matcher = pattern.matcher(text);
//        if (matcher.find()) {
//            String eduBlock = matcher.group(1);
//            Pattern eduPattern = Pattern.compile(
//                    "(B\\.Tech|M\\.Tech|BSc|MSc|BE|ME|Bachelor|Master)[^\\n]*", Pattern.CASE_INSENSITIVE);
//            Matcher eduMatcher = eduPattern.matcher(eduBlock);
//            while (eduMatcher.find()) {
//                String line = eduMatcher.group();
//                Education edu = new Education();
//                // Example: "B.Tech in Computer Science, XYZ University, 2016-2020"
//                String[] parts = line.split(",");
//                if (parts.length >= 3) {
//                    edu.setDegree(parts[0].replaceAll(" in .*", "").trim());
//                    edu.setField(parts[0].replaceAll(".* in ", "").trim());
//                    edu.setInstitution(parts[1].trim());
//                    String[] years = parts[2].replaceAll("[^\\d\\-]", "").split("-");
//                    if (years.length == 2) {
//                        edu.setStartYear(years[0]);
//                        edu.setEndYear(years[1]);
//                    }
//                }
//                list.add(edu);
//            }
//        }
//        return list;
//    }

    private List<Education> extractEducationList(String text) {
        List<Education> eduList = new ArrayList<>();
        Pattern sectionPattern = Pattern.compile("Education\\s+([\\s\\S]+?)\\s+Summary", Pattern.CASE_INSENSITIVE);
        Matcher sectionMatcher = sectionPattern.matcher(text);

        if (sectionMatcher.find()) {
            String eduBlock = sectionMatcher.group(1);
            // Pattern matches degrees as in B.Tech in ..., School ..., etc.
            Pattern degreePat = Pattern.compile("(B\\.Tech|Higher Secondary)[^\\n]*\\n+([\\s\\S]+?)(?=(\\n{2,}|$))", Pattern.CASE_INSENSITIVE);
            Matcher eduMatcher = degreePat.matcher(eduBlock);
            while (eduMatcher.find()) {
                Education edu = new Education();
                String degreeLine = eduMatcher.group(1).trim();
                String rest = eduMatcher.group(2).replaceAll("\\s*\\n\\s*", " ");
                edu.setDegree(degreeLine);
                // Try extract field from rest if applicable, likewise school:
                Matcher inst = Pattern.compile("Maulana Abul Kalam Azad University of Technology").matcher(rest);
                if (inst.find()) edu.setInstitution(inst.group());
                Matcher years = Pattern.compile("Passing Year: ([A-Za-z]+ [\\d]{4})").matcher(rest);
                if (years.find()) edu.setEndYear(years.group(1));
                Matcher cgpa = Pattern.compile("CGPA: ([\\d.]+)").matcher(rest);
                if (cgpa.find()) edu.setField("CGPA: " + cgpa.group(1));
                eduList.add(edu);
            }
        }
        return eduList;
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
