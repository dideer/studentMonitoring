package javawithdatabase.crudspringboot.service;

import javawithdatabase.crudspringboot.model.Teacher;
import javawithdatabase.crudspringboot.model.TeacherSession;

import java.time.LocalDate;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface SessionService {


    TeacherSession addTeacherSession(TeacherSession teacherSession);
    TeacherSession getTeacherSession(Long session_id);
    List<TeacherSession> getAllTeacherSessions();

    List<TeacherSession> getTeacherSessionByTeacherId(Long teacher_id, LocalDate date);

    String getTotalDurationForLevelInCurrentWeek(Long levelId);

    String getTotalDurationForLevelInDay(Long levelId);

    String getTotalDurationForLevelInMonth(Long levelId, int month);

    List<TeacherSession> getTeacherSessionByLevelIdInDay(Long levelId);
    List<TeacherSession> getTeacherSessionByLevelIdInCurrentWeek(Long levelId);
    List<TeacherSession> getTeacherSessionByLevelIdInMonth(Long levelId, int month);


}
