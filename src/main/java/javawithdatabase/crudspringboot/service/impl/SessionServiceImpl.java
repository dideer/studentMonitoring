package javawithdatabase.crudspringboot.service.impl;

import javawithdatabase.crudspringboot.model.Teacher;
import javawithdatabase.crudspringboot.model.TeacherSession;
import javawithdatabase.crudspringboot.repository.SessionRepository;
import javawithdatabase.crudspringboot.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;


    @Override
    public TeacherSession addTeacherSession(TeacherSession teacherSession) {
        return sessionRepository.save(teacherSession);
    }

    @Override
    public TeacherSession getTeacherSession(Long session_id) {
        return sessionRepository.findById(session_id).get();
    }

    @Override
    public List<TeacherSession> getAllTeacherSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public List<TeacherSession> getTeacherSessionByTeacherId(Long teacher_id, LocalDate date) {
        return sessionRepository.findByTeacherId(teacher_id,date);
    }

    @Override
    public String getTotalDurationForLevelInCurrentWeek(Long levelId) {

        // Calculate the start (Monday) and end (Sunday) of the current week
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

        // Fetch sessions for the current week
        List<TeacherSession> sessions = sessionRepository.findByLevelIdForCurrentWeek(levelId, startOfWeek, endOfWeek);

        int totalMinutes = 0;

        // Sum up durations for all sessions
        for (TeacherSession session : sessions) {
            String[] parts = session.getDuration().split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            totalMinutes += hours * 60 + minutes;
        }

        // Convert total minutes back to HH:mm format
        int totalHours = totalMinutes / 60;
        int remainingMinutes = totalMinutes % 60;
        return String.format("%02d:%02d", totalHours, remainingMinutes);
    }

    @Override
    public String getTotalDurationForLevelInDay(Long levelId) {

        LocalDate date = LocalDate.now();

        // Fetch sessions for the current week
        List<TeacherSession> sessions = sessionRepository.findByLevelIdAndDate(levelId,date);
        int totalMinutes = 0;
        for (TeacherSession session : sessions) {
            String[] parts = session.getDuration().split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            totalMinutes += hours * 60 + minutes;
        }

        // Convert total minutes back to HH:mm format
        int totalHours = totalMinutes / 60;
        int remainingMinutes = totalMinutes % 60;
        return String.format("%02d:%02d", totalHours, remainingMinutes);




    }

    @Override
    public String getTotalDurationForLevelInMonth(Long levelId, int month) {

        int year = LocalDate.now().getYear();

        // Create a YearMonth instance for the specified month and current year
        YearMonth yearMonth = YearMonth.of(year, month);

        // Calculate the start and end date of the given month
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        // Retrieve all teacher sessions for the specified level within the month using the custom query
        List<TeacherSession> sessions = sessionRepository.findSessionsByLevelIdAndMonth(levelId, startDate, endDate);

        // Calculate total duration from the retrieved sessions
        long totalMinutes = sessions.stream()
                .mapToLong(session -> {
                    String[] parts = session.getDuration().split(":");
                    int hours = Integer.parseInt(parts[0]);
                    int minutes = Integer.parseInt(parts[1]);
                    return hours * 60 + minutes;
                })
                .sum();

        // Convert total minutes back to HH:mm format
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;
        return String.format("%02d:%02d", hours, minutes);
    }

    @Override
    public List<TeacherSession> getTeacherSessionByLevelIdInDay(Long levelId) {
        LocalDate date = LocalDate.now();

        // Fetch sessions for the current week
        List<TeacherSession> sessions = sessionRepository.findByLevelIdAndDate(levelId,date);
        return sessions;
    }

    @Override
    public List<TeacherSession> getTeacherSessionByLevelIdInCurrentWeek(Long levelId) {
        // Calculate the start (Monday) and end (Sunday) of the current week
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

        // Fetch sessions for the current week
        List<TeacherSession> sessions = sessionRepository.findByLevelIdForCurrentWeek(levelId, startOfWeek, endOfWeek);

        return sessions;
    }

    @Override
    public List<TeacherSession> getTeacherSessionByLevelIdInMonth(Long levelId, int month) {

        int year = LocalDate.now().getYear();

        // Create a YearMonth instance for the specified month and current year
        YearMonth yearMonth = YearMonth.of(year, month);

        // Calculate the start and end date of the given month
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        // Retrieve all teacher sessions for the specified level within the month using the custom query
        List<TeacherSession> sessions = sessionRepository.findSessionsByLevelIdAndMonth(levelId, startDate, endDate);
        return sessions;
    }

}
