package javawithdatabase.crudspringboot.controller;

import javawithdatabase.crudspringboot.model.Attendance;
import javawithdatabase.crudspringboot.model.SubjectToTeacher;
import javawithdatabase.crudspringboot.model.TeacherSession;
import javawithdatabase.crudspringboot.model.User;
import javawithdatabase.crudspringboot.service.AttendanceService;
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
import java.time.LocalDate;
import java.util.List;

@Controller
public class SubjectToTeacherController {


    @Autowired
    private SubjectToTeacherService subjectToTeacherService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/admin/SubToTeach")
    public String SubToTeach(Model model,Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());


        model.addAttribute("subjectToTeachers", subjectToTeacherService.findAllSubjectToTeachers());
        return "SubToTeachList";
    }

//    @PostMapping("/addSubToTeach")
//    public String addSubToTeach(@RequestParam("teacherId") Long teacherId, @RequestParam("subjectCode") String subjectCode) {
//
//        System.out.println("wertyutttyty" +teacherId);
//        System.out.println("wertyutttyty" +subjectCode);
//
//
//        Teacher teacher = teacherService.getTeacherById(teacherId);
//
//
//        Subject subject = subjectService.getSubjectById(subjectCode);
//
//        SubjectToTeacher subjectToTeacher = new SubjectToTeacher(subject, teacher);
//
//        subjectToTeacherService.saveSubjectToTeacher(subjectToTeacher);
//
//        return "redirect:/SubToTeach";
//    }



    @PostMapping("/admin/addSubToTeach")
    public String addSubToTeach(@ModelAttribute("teacherss") SubjectToTeacher subjectToTeacher){

        subjectToTeacherService.saveSubjectToTeacher(subjectToTeacher);
        return "redirect:/admin/SubToTeach";
    }

    @GetMapping("/teacherById")
    public String teacherById() {

        return "/findTeacherById";

    }

//    @PostMapping("/findteacherById")
//    public String searchTeacher(@RequestParam Long teacherId, Model model) {
//
//        Teacher teacher = teacherService.getTeacherById(teacherId);
//
//        List<SubjectToTeacher> subjectToTeacher = subjectToTeacherService.findSubjectToTeacherById(teacherId);
//
//        model.addAttribute("teacher", teacher);
//
//
//        model.addAttribute("subjectIT", subjectToTeacher);
//        return "teacherAndSubject";
//    }

    @GetMapping("/teacher/addSession/{subjectCode}")
    public String addSession(Model model, @PathVariable("subjectCode") String subjectCode, Principal principal) {


        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();


        SubjectToTeacher subjectByCode = subjectToTeacherService.findSubjectByCode(subjectCode);
        model.addAttribute("subject", subjectByCode);

        return "teacher/addSession";

    }

    @GetMapping("/teacher/reportSession/{subjectCode}")
    public String reportSession(Model model, @PathVariable("subjectCode") String subjectCode, Principal principal) {


        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        LocalDate now = LocalDate.now();

        List<Attendance> dailReport = attendanceService.teacherDailAttendance(subjectCode,now);
        model.addAttribute("subject", dailReport);

        return "teacher/TeacherDailyAttendance";

    }






}
