package javawithdatabase.crudspringboot.service.impl;

import javawithdatabase.crudspringboot.model.Attendance;
import javawithdatabase.crudspringboot.model.gateAttendance;
import javawithdatabase.crudspringboot.repository.GateAttendanceRepo;
import javawithdatabase.crudspringboot.service.GateAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Service
public class GateAttendanceImpl implements GateAttendanceService {

    @Autowired
    private GateAttendanceRepo gateAttendanceRepo;

    @Override
    public List<gateAttendance> getTodayAttendanceByLevel(Long levelId) {

        LocalDate today = LocalDate.now();


        return gateAttendanceRepo.findTodayAttendanceByLevel(today,levelId);
    }

    @Override
    public List<gateAttendance> getTodayAttendanceByLevels(Long levelId) {

        LocalDate today = LocalDate.now();


        return gateAttendanceRepo.findTodayAttendanceByLevels(today,levelId);
    }

    @Override
    public List<gateAttendance> weeklyReport(Long levelId) {

        LocalDate startDate = LocalDate.now().with(DayOfWeek.MONDAY);  // Start of the week
        LocalDate endDate = LocalDate.now().with(DayOfWeek.SUNDAY);    // End of the week

        return gateAttendanceRepo.findWeeklyAttendanceByLevel(startDate,endDate,levelId);
    }

    @Override
    public List<gateAttendance> monthlyReport(Long levelId, int month) {

        int currentYear = LocalDate.now().getYear();

        // Use YearMonth to get the first and last day of the selected month in the current year
        YearMonth yearMonth = YearMonth.of(currentYear, month);
        LocalDate startDate = yearMonth.atDay(1); // First day of the month
        LocalDate endDate = yearMonth.atEndOfMonth(); // Last day of the month



        List<gateAttendance> attendanceList = gateAttendanceRepo.findMonthlyAttendanceByLevel(startDate, endDate,levelId);

        return attendanceList;
    }
}
