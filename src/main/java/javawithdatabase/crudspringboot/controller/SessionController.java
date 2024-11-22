package javawithdatabase.crudspringboot.controller;

import javawithdatabase.crudspringboot.model.Student;
import javawithdatabase.crudspringboot.model.TeacherSession;
import javawithdatabase.crudspringboot.model.User;
import javawithdatabase.crudspringboot.model.gateAttendance;
import javawithdatabase.crudspringboot.service.GateAttendanceService;
import javawithdatabase.crudspringboot.service.SessionService;
import javawithdatabase.crudspringboot.service.StudentService;
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
public class SessionController {


    @Autowired
    private SessionService sessionService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private GateAttendanceService gateAttendanceService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/teacher/getAllSession")
    public String getAllSession(Model model, Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());
        User user1 = user.getUser();

        LocalDate today = LocalDate.now();

        model.addAttribute("teacherSession",sessionService.getTeacherSessionByTeacherId(user1.getTeacher().getTeacherId(),today));

        return "/teacher/getAllSession";
    }


    @PostMapping("/teacher/saveSession")
    public String saveSession(@ModelAttribute("saveSession") TeacherSession teacherSession) {

        sessionService.addTeacherSession(teacherSession);


        return "redirect:/teacher/getAllSession";
    }

    @GetMapping("/teacher/makeAttendence/{session_id}")
    public String makeAttendance(@PathVariable("session_id") Long session_id, Model model,Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());
        User user1 = user.getUser();

        TeacherSession teacherSession = sessionService.getTeacherSession(session_id);

        if (teacherSession != null) {
            Long levelId = teacherSession.getLevel().getLevelId();

            List<Student> students = studentService.getStudentsByLevelId(levelId);


            List<gateAttendance> gate = gateAttendanceService.getTodayAttendanceByLevel(levelId);

            model.addAttribute("students", students);
            model.addAttribute("teacherSession", teacherSession);
            model.addAttribute("level", levelId);
        }



        return "/teacher/makeAttendance";
    }





    @GetMapping("/session/daily/{levelId}")
    public String sessionDaily(@PathVariable("levelId") Long levelId, Model model, Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());

        List<TeacherSession> dailySessions = sessionService.getTeacherSessionByLevelIdInDay(levelId);

        model.addAttribute("allSessions", dailySessions);


        return "/hod/dailySession";
    }

    @GetMapping("/session/weekly/{levelId}")
    public String sessionCurrentWeek(@PathVariable("levelId") Long levelId, Model model, Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());

        List<TeacherSession> weeklySessions = sessionService.getTeacherSessionByLevelIdInCurrentWeek(levelId);
        model.addAttribute("allSessions", weeklySessions);
        return "/hod/weekSession";
    }
    @GetMapping("/session/monthly/{levelId}/report")
    public String sessionMonth(
            @PathVariable("levelId") Long levelId,
            @RequestParam("month") int month,
            Model model, Principal principal) {



        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());

        List<TeacherSession> monthSessions = sessionService.getTeacherSessionByLevelIdInMonth(levelId, month);
        model.addAttribute("allSessions", monthSessions);

        return "/hod/monthSession";
    }






}
