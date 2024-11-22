package javawithdatabase.crudspringboot.controller;

import javawithdatabase.crudspringboot.model.*;
import javawithdatabase.crudspringboot.service.*;
import javawithdatabase.crudspringboot.service.sec.CustomerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class AttendanceController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private LevelService levelService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SessionService teacherSessionService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectToTeacherService subjectToTeacherService;

    @PostMapping("/saveAttendance")
    public String saveAttendance(@RequestParam Map<String, String> allParams, Model model, Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());


        // Extract sessionId from parameters
        Long sessionId = Long.parseLong(allParams.get("sessionId"));

//         Retrieve the TeacherSession by sessionId

        TeacherSession teacherSession = teacherSessionService.getTeacherSession(sessionId);
        if (teacherSession == null) {
            model.addAttribute("message", "Invalid session ID");
            return "errorsss"; // Return to an error page or appropriate view
        }

        // Iterate through parameters to extract student IDs, statuses, and durations
        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.startsWith("status_")) {
                String studentId = key.substring("status_".length()); // Extract studentId from key
                String status = value;

                // Retrieve the duration for this studentD
                String durationKey = "duration_" + studentId;
                String duration = allParams.getOrDefault(durationKey, "00:00");

                // Retrieve the Student entity by studentId
                Student student = studentService.getStudentById(studentId);
                if (student == null) {
                    model.addAttribute("message", "Invalid student ID: " + studentId);
                    return "error"; // Return to an error page or appropriate view
                }

                // Create and save Attendance record
                Attendance attendance = new Attendance();
                attendance.setTeacherSession(teacherSession);
                attendance.setStudent(student);
                attendance.setStatus(status);
                attendance.setDuration(duration);
                attendance.setTimestamp(LocalDateTime.now());

                attendanceService.saveAttendance(attendance);


            }
        }

        model.addAttribute("message", "Attendance marked successfully");

        Teacher teacher = teacherService.getTeacherById(user1.getTeacher().getTeacherId());

        List<SubjectToTeacher> subjectToTeacher = subjectToTeacherService.findSubjectToTeacherById(user1.getTeacher().getTeacherId());

        model.addAttribute("teacher", teacher);


        model.addAttribute("subjectIT", subjectToTeacher);
        return "teacher/teacherAndSubject"; // Return to a success page or appropriate view
    }


    @GetMapping("/allAttendance")
    public String allAttendance(Model model) {
        model.addAttribute("attendances", attendanceService.getAllAttendances());
        return "allAttendance";
    }

    @GetMapping("/report")
    public String report() {
        return "attendanceReport";
    }

    @GetMapping("/allLevel")
    public String getLevel(@ModelAttribute("level") Level level, Model model) {
        model.addAttribute("departments", departmentService.AllDepartments());
        model.addAttribute("levels", levelService.getAllLevels());
        return "displayAllLevel";
    }



}
