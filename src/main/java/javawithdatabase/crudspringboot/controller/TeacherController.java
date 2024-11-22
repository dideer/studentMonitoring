package javawithdatabase.crudspringboot.controller;

import javawithdatabase.crudspringboot.model.Subject;
import javawithdatabase.crudspringboot.model.SubjectToTeacher;
import javawithdatabase.crudspringboot.model.Teacher;
import javawithdatabase.crudspringboot.model.User;
import javawithdatabase.crudspringboot.service.SubjectService;
import javawithdatabase.crudspringboot.service.SubjectToTeacherService;
import javawithdatabase.crudspringboot.service.TeacherService;
import javawithdatabase.crudspringboot.service.sec.CustomerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectToTeacherService subjectToTeacherService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/admin/teacher")
    public String teacher(@ModelAttribute("teacher") Teacher teacher, Model model,Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "/teacher";
    }

    @PostMapping("/admin/addTeacher")
    public String addTeacher(@ModelAttribute("teacher") Teacher teacher, Model model) {

        teacherService.createTeacher(teacher);

        return "redirect:/admin/teacher";
    }






    @GetMapping("/teacher/")
    public String index(Model model,Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);

        return "/teacher/index";
    }



    @GetMapping("/teacher/registerSession")

    public String teachers(Model model, Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());




        Teacher teacher = teacherService.getTeacherById(user1.getTeacher().getTeacherId());

        List<SubjectToTeacher> subjectToTeacher = subjectToTeacherService.findSubjectToTeacherById(user1.getTeacher().getTeacherId());

        model.addAttribute("teacher", teacher);


        model.addAttribute("subjectIT", subjectToTeacher);
        return "teacher/teacherAndSubject";

    }

    @GetMapping("/admin/teacher/add/{teacherId}")
    public String teacherById(@PathVariable("teacherId") Long teacherId, Model model,Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());


        model.addAttribute("subjects", subjectService.findUnassignedSubjects());

            model.addAttribute("teacherss", teacherService.getTeacherById(teacherId));
            model.addAttribute("teachers", teacherService.getAllTeachers());
            return "/teacherToSubject";
    }


    @GetMapping("/admin/teacher/addUser/{teacherId}")
    public String teacherByIdd(@PathVariable("teacherId") Long teacherId, Model model, Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());
        model.addAttribute("subjects",subjectService.getAllSubjects());

        model.addAttribute("teacherss", teacherService.getTeacherById(teacherId));
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "/registerUser";
    }

//    @PostMapping("/findteacherById")
//    public String findTeacherById(@RequestParam Long id, Model model){
//        Teacher teacherId = teacherService.getTeacherById(id);
//        model.addAttribute("teachers", teacherId);
//        return "findTeacherById";
//    }





//    @PostMapping("/findTeacherById")
//    public String searchTeacher(@RequestParam Long id, Model model) {
//        Teacher teacher = teacherService.getTeacherById(id);
//        model.addAttribute("teachers", teacher);
//        model.addAttribute("searchPerformed", true);
//        return "findTeacherById";
//    }
}
