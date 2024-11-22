package javawithdatabase.crudspringboot.service;

import javawithdatabase.crudspringboot.model.Attendance;
import org.hibernate.Session;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface AttendanceService {

    Attendance saveAttendance(Attendance attendance);
    List<Attendance> getAllAttendances();




    List<Object[]> getDailyAttendance(Long levelId, LocalDate date);
    List<Attendance> getMonthlyAttendanceByLevel(Long levelId, int month);

    List<Object[]> getMonthlyAttendanceByLevels(Long levelId, int month);
    List<Object[]> getWeeklyAttendanceByLevels(Long levelId, LocalDate startDate, LocalDate endDate);

    List<Attendance> getDailyAttendanceByDate(Long levelId,LocalDate date);

    List<Attendance> teacherDailAttendance(String subjectCode,LocalDate date);



}
