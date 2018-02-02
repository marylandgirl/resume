package com.example.resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    ResumeRepository  resumeRepository;

    @Autowired
    ProfileRepository profileRepository;

    @RequestMapping("/")
    public String listProfile(Model model) {
        model.addAttribute("profiles", profileRepository.findAll());
        return "profile";
    }

    @GetMapping("/addprofile")
    public String profileForm(Model model){
        model.addAttribute("profile",new Profile());
        return "profileform";
    }

    @PostMapping("/processprofile")public String processProfileForm(@Valid @ModelAttribute("profile") Profile profile, BindingResult result){
        if (result.hasErrors()) {
            return "jobform";
        }
        profileRepository.save(profile);
        return "redirect:/listexp";
    }

    @RequestMapping("/listexp")
    public String listExperiences(Model model) {
        model.addAttribute("resumes", resumeRepository.findAll());
        return "experiences";
    }

    @GetMapping("/addjobs")
    public String jobForm(Model model){
        model.addAttribute("resume",new Resume());
        return "jobform";
    }

    @PostMapping("/process")public String processForm(@Valid @ModelAttribute("resume") Resume resume, BindingResult result){
        if (result.hasErrors()) {
            return "jobform";
        }
        resumeRepository.save(resume);
        return "redirect:/listexp";
    }

    @RequestMapping("/detail/{id}")
    public String showJob(@PathVariable("id") long id, Model model) {
        model.addAttribute("resume", resumeRepository.findOne(id));
        return "show";
    }

}
