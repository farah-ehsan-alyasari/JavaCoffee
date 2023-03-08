package com.javacoffee.JavaCoffee.adminPackage.controller;

import com.javacoffee.JavaCoffee.adminPackage.entity.Job;
import com.javacoffee.JavaCoffee.adminPackage.service.JobService;
import com.javacoffee.JavaCoffee.adminPackage.service.UploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@Slf4j
public class JobController {

    private final String UPLOAD_DIR = "src/main/resources/uploads";

    @Autowired
    UploadService uploadService;

    @Autowired
    JobService jobService;

    @RequestMapping("/apply-here")
    public String showApplyHerePage(Model model)
    {
        model.addAttribute("upload", new Job());
        return "apply-here";
    }

    @PostMapping("/upload-process")
    public String singUpProcess(@ModelAttribute("upload") Job job,
                                Model model, @RequestParam("file") MultipartFile file, RedirectAttributes attributes) throws Exception {

        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload.");
            return "redirect:/apply-here";
        }

        // Save the job object to the database
        String fullName = job.getFullName();
        job.setUploadedFile(file.getOriginalFilename());
        job.setFullName(fullName);
        jobService.saveJob(job);

        model.addAttribute(file);
        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        log.debug("File name {} " + fileName);
        uploadService.encryptPDFFile(UPLOAD_DIR, fileName, file, UUID.randomUUID().toString());
        return "apply-here-success";
    }

}
