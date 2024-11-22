package javawithdatabase.crudspringboot.controller;

import javawithdatabase.crudspringboot.model.Attendance;
import javawithdatabase.crudspringboot.model.User;
import javawithdatabase.crudspringboot.model.gateAttendance;
import javawithdatabase.crudspringboot.service.AttendanceService;
import javawithdatabase.crudspringboot.service.GateAttendanceService;
import javawithdatabase.crudspringboot.service.SessionService;
import javawithdatabase.crudspringboot.service.sec.CustomerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Controller
public class ReportController {
    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private GateAttendanceService  gateAttendanceService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/report/daily")
    public String getDailyReport(@RequestParam Long levelId, @RequestParam LocalDate date, Model model, Principal principal) {



        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());




        List<Object[]> attendanceReports = attendanceService.getDailyAttendance(levelId, date);
        String totalDuration = sessionService.getTotalDurationForLevelInDay(levelId);
        model.addAttribute("totalDuration", totalDuration);
        model.addAttribute("attendanceReports", attendanceReports);

        return "dailyReport"; // Name of the Thymeleaf template
    }

    @GetMapping("/report/monthly/{levelId}")
    public String showMonthlySelectionPage(@PathVariable("levelId") Long levelId, Model model,Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());



        // Pass the levelId to the view so we can include it in the form
        model.addAttribute("levelId", levelId);
        return "hod/monthlyReport";  // Return the Thymeleaf template for month selection
    }

    @GetMapping("/report/monthlys/{levelId}/report")
    public String MonthlyReport(
            @PathVariable("levelId") Long levelId,
            @RequestParam("month") int month,
            Model model, Principal principal) {



        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());

        // Fetch monthly attendance data
        List<Object[]> MonthlyAttendance = attendanceService.getMonthlyAttendanceByLevels(levelId, month);
        String totalDuration = sessionService.getTotalDurationForLevelInMonth(levelId,month);


        // Add data to the model to be accessed in the view
        model.addAttribute("attendanceRecords", MonthlyAttendance);
        model.addAttribute("selectedMonth", month);
        model.addAttribute("levelId", levelId);
        model.addAttribute("totalDuration", totalDuration);

        return "hod/monthlyReportView"; // The view template name to render
    }
    @GetMapping("/report/gateMonthly/{levelId}/report")
    public String getMonthlyReport(
            @PathVariable("levelId") Long levelId,
            @RequestParam("month") int month,
            Model model, Principal principal) {



        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());

        // Fetch monthly attendance data
        List<gateAttendance> gateMonthlyAttendance = gateAttendanceService.monthlyReport(levelId, month);

        // Add data to the model to be accessed in the view
        model.addAttribute("attendanceRecords", gateMonthlyAttendance);
        model.addAttribute("selectedMonth", month);
        model.addAttribute("levelId", levelId);


        return "hod/gateMonthlyReport"; // The view template name to render
    }

    @GetMapping("/report/weekly/{levelId}")
    public String getWeeklyReport(@PathVariable("levelId") Long levelId,
                                     Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);
        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(principal.getName());
        User user1 = user.getUser();
        model.addAttribute("user", user1);
        model.addAttribute("userId", user1.getUserId());



        // Get the start and end of the current week (Monday to Sunday)
        LocalDate now = LocalDate.now();
        LocalDate startDates = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endDates = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

        List<Object[]> weeklyAttendance = attendanceService.getWeeklyAttendanceByLevels(levelId, startDates, endDates);
        String totalDuration = sessionService.getTotalDurationForLevelInCurrentWeek(levelId);
        model.addAttribute("attendanceRecords", weeklyAttendance);
        model.addAttribute("totalDuration", totalDuration);

        return "hod/weeklyReportView"; // Adjust this to your actual view name
    }


    @GetMapping("/report/daily/{levelId}")
    public String getDailyReport(@PathVariable("levelId") Long levelId,
                                  Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);
        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(principal.getName());
        User user1 = user.getUser();
        model.addAttribute("user", user1);
        model.addAttribute("userId", user1.getUserId());



        // Get the start and end of the current week (Monday to Sunday)
        List<gateAttendance> dailyAttendance = gateAttendanceService.getTodayAttendanceByLevels(levelId);
        model.addAttribute("attendanceRecords", dailyAttendance);
        return "hod/gateDailyReport"; // Adjust this to your actual view name
    }

    @GetMapping("/report/gateWeekly/{levelId}")
    public String gateWeeklyReport(@PathVariable("levelId") Long levelIds,Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);
        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(principal.getName());
        User user1 = user.getUser();
        model.addAttribute("user", user1);
        model.addAttribute("userId", user1.getUserId());



        // Get the start and end of the current week (Monday to Sunday)
        List<gateAttendance> weeklyAttendance = gateAttendanceService.weeklyReport(levelIds);
        model.addAttribute("attendanceRecords", weeklyAttendance);
        return "hod/gateWeeklyReport"; // Adjust this to your actual view name
    }
}
