package javawithdatabase.crudspringboot.repository;

import javawithdatabase.crudspringboot.model.Attendance;
import javawithdatabase.crudspringboot.model.Teacher;
import javawithdatabase.crudspringboot.model.TeacherSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface SessionRepository extends JpaRepository<TeacherSession,Long> {


    @Query("SELECT s FROM TeacherSession s WHERE s.teacher.teacherId = :teacherId AND s.date = :toDays " +
            "AND s.session_id NOT IN (SELECT a.teacherSession.session_id FROM Attendance a)")
    List<TeacherSession> findByTeacherId(Long teacherId, LocalDate toDays);




    @Query("SELECT s " +
            "FROM  TeacherSession s " +
            "WHERE  s.level.LevelId = :levelId " +
            "AND s.date = :date " +
            "ORDER BY s.level.LevelId")
    List<TeacherSession> findDailyAttendanceByLevel(Long levelId, LocalDate date);



    @Query("SELECT s " +
            "FROM TeacherSession s " +
            "WHERE s.level.LevelId = :levelId " +
            "AND s.date BETWEEN :startDate AND :endDate " +
            "ORDER BY s.level.LevelId")
    List<TeacherSession> findWeeklySessionByLevel(Long levelId, LocalDate startDate, LocalDate endDate);



    @Query("SELECT ts FROM TeacherSession ts WHERE ts.level.LevelId = :levelId " +
            "AND ts.date BETWEEN :startDate AND :endDate")
    List<TeacherSession> findByLevelIdForCurrentWeek(@Param("levelId") Long levelId,
                                                     @Param("startDate") LocalDate startDate,
                                                     @Param("endDate") LocalDate endDate);

    @Query("SELECT ts FROM TeacherSession ts WHERE ts.level.LevelId = :levelId AND ts.date = :date")
    List<TeacherSession> findByLevelIdAndDate(Long levelId, LocalDate date);

    @Query("SELECT ts FROM TeacherSession ts WHERE ts.level.LevelId = :levelId AND ts.date BETWEEN :startDate AND :endDate")
    List<TeacherSession> findSessionsByLevelIdAndMonth(
            @Param("levelId") Long levelId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}

