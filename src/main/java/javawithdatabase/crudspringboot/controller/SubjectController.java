package javawithdatabase.crudspringboot.controller;

import javawithdatabase.crudspringboot.model.Level;
import javawithdatabase.crudspringboot.model.Subject;
import javawithdatabase.crudspringboot.model.User;
import javawithdatabase.crudspringboot.service.LevelService;
import javawithdatabase.crudspringboot.service.SubjectService;
import javawithdatabase.crudspringboot.service.sec.CustomerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private LevelService levelService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/subject/getSubject")
    public String getSubject(@ModelAttribute("subject") Subject subject, Model model, Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());


        model.addAttribute("level",levelService.getAllLevels());
        model.addAttribute("subjects",subjectService.getAllSubjects());
        return "subject";
    }
    @PostMapping("/subject/addSubject")
    public String addSubject(@ModelAttribute("subject") Subject subject, Model model) {

        subjectService.saveSubject(subject);

        return "redirect:/admin/subject/getSubject";

    }

    @GetMapping("/level/subject/{levelId}")
    public String getSubjectsByLevel(@PathVariable("levelId") Long levelId, Model model,Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());


        List<Subject> subjects = subjectService.getSubjectsByLevelId(levelId);

        Level level = levelService.getLevelById(levelId);

        model.addAttribute("level", level);
        model.addAttribute("subjects", subjects);
        return "subjectsByLevel"; // This should be the name of your Thymeleaf template to display subjects
    }




}
