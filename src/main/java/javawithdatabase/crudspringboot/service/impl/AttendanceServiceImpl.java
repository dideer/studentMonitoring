package javawithdatabase.crudspringboot.service.impl;

import javawithdatabase.crudspringboot.model.Attendance;
import javawithdatabase.crudspringboot.model.TeacherSession;
import javawithdatabase.crudspringboot.repository.AttendanceRepository;
import javawithdatabase.crudspringboot.repository.SessionRepository;
import javawithdatabase.crudspringboot.service.AttendanceService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private SessionRepository sessionRepository;
    @Override
    public Attendance saveAttendance(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }



    @Override
    public List<Object[]> getDailyAttendance(Long levelId, LocalDate date) {



        List<Attendance> dailyAttendance = attendanceRepository.findDailyAttendanceByLevel(levelId, date);

        Map<String, Object[]> studentDataMap = new HashMap<>(); // Map to hold total duration and other data for each student
        for (Attendance attendance : dailyAttendance) {
            String studentId = attendance.getStudent().getStudentId();
            String firstName = attendance.getStudent().getFirstName();
            String lastName = attendance.getStudent().getLastName();
            String phoneNumber = attendance.getStudent().getParentPhoneNumber();
            String durationString = attendance.getDuration();
            int durationInMinutes = convertDurationToMinutes(durationString);

            // Summing the duration and storing other fields
            if (studentDataMap.containsKey(studentId)) {
                Object[] existingData = studentDataMap.get(studentId);
                existingData[3] = (int) existingData[3] + durationInMinutes; // Sum durations
            } else {
                // Storing initial values: studentId, firstName, lastName, phoneNumber, and duration
                studentDataMap.put(studentId, new Object[]{firstName, lastName, phoneNumber, durationInMinutes});
            }
        }

        // Convert the result to a list of Object[]
        List<Object[]> results = new ArrayList<>();
        for (Map.Entry<String, Object[]> entry : studentDataMap.entrySet()) {
            String studentId = entry.getKey();
            Object[] data = entry.getValue();
            results.add(new Object[]{studentId, data[0], data[1], data[2], convertMinutesToDuration((int) data[3])});
        }

        return results;

    }

    @Override
    public List<Attendance> getMonthlyAttendanceByLevel(Long levelId, int month) {
        Year currentYear = Year.now();

        // Start of the month at 00:00:00
        LocalDateTime startDate = LocalDate.of(currentYear.getValue(), month, 1).atStartOfDay();

        // End of the month at 23:59:59
        LocalDateTime endDate = startDate.withDayOfMonth(startDate.toLocalDate().lengthOfMonth()).withHour(23).withMinute(59).withSecond(59);

        // Fetch attendance records for the month using LocalDateTime
        return attendanceRepository.findMonthlyAttendanceByLevel(levelId, startDate, endDate);

    }

    @Override
    public List<Object[]> getMonthlyAttendanceByLevels(Long levelId, int month) {

        Year currentYear = Year.now();
        LocalDateTime startDate = LocalDate.of(currentYear.getValue(), month, 1).atStartOfDay();
        LocalDateTime endDate = startDate.withDayOfMonth(startDate.toLocalDate().lengthOfMonth()).withHour(23).withMinute(59).withSecond(59);

        List<Attendance> attendanceList = attendanceRepository.findMonthlyAttendanceByLevel(levelId, startDate, endDate);
        // Summing durations manually and collecting additional student data
        Map<String, Object[]> studentDataMap = new HashMap<>(); // Map to hold total duration and other data for each student
        for (Attendance attendance : attendanceList) {
            String studentId = attendance.getStudent().getStudentId();
            String firstName = attendance.getStudent().getFirstName();
            String lastName = attendance.getStudent().getLastName();
            String phoneNumber = attendance.getStudent().getParentPhoneNumber();
            String durationString = attendance.getDuration();
            int durationInMinutes = convertDurationToMinutes(durationString);

            // Summing the duration and storing other fields
            if (studentDataMap.containsKey(studentId)) {
                Object[] existingData = studentDataMap.get(studentId);
                existingData[3] = (int) existingData[3] + durationInMinutes; // Sum durations
            } else {
                // Storing initial values: studentId, firstName, lastName, phoneNumber, and duration
                studentDataMap.put(studentId, new Object[]{firstName, lastName, phoneNumber, durationInMinutes});
            }
        }

        // Convert the result to a list of Object[]
        List<Object[]> results = new ArrayList<>();
        for (Map.Entry<String, Object[]> entry : studentDataMap.entrySet()) {
            String studentId = entry.getKey();
            Object[] data = entry.getValue();
            results.add(new Object[]{studentId, data[0], data[1], data[2], convertMinutesToDuration((int) data[3])});
        }

        return results;

    }

    @Override
    public List<Object[]> getWeeklyAttendanceByLevels(Long levelId, LocalDate startDate, LocalDate endDate) {

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.atTime(23, 59, 59);

        // Fetch weekly attendance data
        List<Attendance> attendanceList = attendanceRepository.findWeeklyAttendanceByLevel(levelId, startDateTime, endDateTime);

        // Map to hold total duration and other data for each student
        Map<String, Object[]> studentDataMap = new HashMap<>(); // Map to hold total duration and other data for each student
        for (Attendance attendance : attendanceList) {
            String studentId = attendance.getStudent().getStudentId();
            String firstName = attendance.getStudent().getFirstName();
            String lastName = attendance.getStudent().getLastName();
            String phoneNumber = attendance.getStudent().getParentPhoneNumber();
            String durationString = attendance.getDuration();
            String sessionTry = attendance.getTeacherSession().getDuration();
            int sessionInMinutes = convertDurationToMinutes(sessionTry);
            int durationInMinutes = convertDurationToMinutes(durationString);

            // Summing the duration and storing other fields
            if (studentDataMap.containsKey(studentId)) {
                Object[] existingData = studentDataMap.get(studentId);
                existingData[3] = (int) existingData[3] + durationInMinutes; // Sum durations
            } else {
                // Storing initial values: studentId, firstName, lastName, phoneNumber, and duration
                studentDataMap.put(studentId, new Object[]{firstName, lastName, phoneNumber, durationInMinutes,sessionInMinutes});
            }
        }

        // Convert the result to a list of Object[]
        List<Object[]> results = new ArrayList<>();
        for (Map.Entry<String, Object[]> entry : studentDataMap.entrySet()) {
            String studentId = entry.getKey();
            Object[] data = entry.getValue();
            results.add(new Object[]{studentId, data[0], data[1], data[2], convertMinutesToDuration((int) data[3]), convertMinutesToDuration((int) data[4]) });
        }

        return results;

    }

    @Override
    public List<Attendance> getDailyAttendanceByDate(Long levelId,LocalDate date) {

        List<Attendance> dailyAttendance = attendanceRepository.findDailyAttendanceByLevel(levelId, date);

        return dailyAttendance;
    }

    @Override
    public List<Attendance> teacherDailAttendance(String Subject, LocalDate date) {
        List<Attendance>  teacherDailyAttendance =  attendanceRepository.findTeacherDailyAttendanceByLevel(Subject,date);
        return teacherDailyAttendance;
    }


    public int convertDurationToMinutes(String duration) {
        // Check if the duration string is in the correct format
        if (duration == null || !duration.matches("\\d{2}:\\d{2}")) {
            throw new IllegalArgumentException("Duration must be in the format HH:mm");
        }

        String[] parts = duration.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);

        return hours * 60 + minutes; // Convert to total minutes
    }

    public String convertMinutesToDuration(int totalMinutes) {
        int hours = totalMinutes / 60; // Calculate hours
        int minutes = totalMinutes % 60; // Calculate remaining minutes

        // Format the hours and minutes to ensure two digits for each
        return String.format("%02d:%02d", hours, minutes);
    }




}
