package javawithdatabase.crudspringboot.controller;

import javawithdatabase.crudspringboot.model.*;
import javawithdatabase.crudspringboot.service.LevelService;
import javawithdatabase.crudspringboot.service.PhotoToStudentService;
import javawithdatabase.crudspringboot.service.StudentService;
import javawithdatabase.crudspringboot.service.sec.CustomerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private LevelService levelService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private PhotoToStudentService photoToStudentService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/admin/getStudent")
    public String getStudent(Model model,Principal principal){



        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());


        model.addAttribute("level",levelService.allLevelByStatus("Active"));

        return "/students";
    }

    @PostMapping("/admin/addStudent")
    public String addStudent(@ModelAttribute("student") Student student, Model model, Principal principal) {

        // Load the logged-in user's details
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);

        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());
        User user1 = user.getUser();

        model.addAttribute("user", user1);
        model.addAttribute("userId", user1.getUserId());

        // Save the student (Insert or Update)
        Student savedStudent = studentService.saveStudent(student);

        // Check if the student was inserted/updated successfully
        if (savedStudent == null || savedStudent.getStudentId() == null) {
            model.addAttribute("message", "Registration failed");
        } else {
            model.addAttribute("message", "Registration successful");
        }

        return "students";
    }


//    @GetMapping("/admin/allStudents")
//    public String allStudents(Model model){
//        model.addAttribute("students",studentService.getAllStudents());
//        return "allStudents";
//    }

    @GetMapping("/admin/allStudents")
    public String allStudentss(Model model,Principal principal){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());

        model.addAttribute("photoToStudent",studentService.getAllStudents());

        model.addAttribute("x",photoToStudentService.getPhotoToStudents());
        return "allStudents";
    }



    @GetMapping("/admin/level/students/{levelId}")
    public String getSubjectsByLevel(@PathVariable("levelId") Long levelId, Model model, Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());

        List<Student> students = studentService.getStudentsByLevelId(levelId);

        Level level = levelService.getLevelById(levelId);

        model.addAttribute("level", level);
        model.addAttribute("students", students);
        return "studentByLevelId"; // This should be the name of your Thymeleaf template to display subjects
    }
}
