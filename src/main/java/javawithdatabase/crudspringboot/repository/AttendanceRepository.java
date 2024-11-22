package javawithdatabase.crudspringboot.repository;

import javawithdatabase.crudspringboot.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    @Query("SELECT a " +
            "FROM Attendance a " +
            "JOIN a.student s " +
            "JOIN s.level l " +
            "WHERE l.LevelId = :levelId " +
            "AND DATE(a.timestamp) = :date " +
            "ORDER BY s.studentId")
    List<Attendance> findDailyAttendanceByLevel(Long levelId, LocalDate date);

    @Query("SELECT a " +
            "FROM Attendance a " +
            "JOIN a.student s " +
            "JOIN a.teacherSession t " +
            "WHERE t.subject.SubjectCode = :subjectId " +
            "AND DATE(a.timestamp) = :date " +
            "ORDER BY s.studentId")
    List<Attendance> findTeacherDailyAttendanceByLevel(String subjectId, LocalDate date);

    @Query("SELECT a " +
            "FROM Attendance a " +
            "JOIN a.student s " +
            "JOIN s.level l " +
            "WHERE l.LevelId = :levelId " +
            "AND a.timestamp BETWEEN :startDate AND :endDate " +
            "ORDER BY s.studentId")
    List<Attendance> findWeeklyAttendanceByLevel(Long levelId, LocalDateTime startDate, LocalDateTime endDate);


    @Query("SELECT a " +
            "FROM Attendance a " +
            "JOIN a.student s " +
            "JOIN s.level l " +
            "WHERE l.LevelId = :levelId " +
            "AND a.timestamp BETWEEN :startDate AND :endDate " +
            "ORDER BY s.studentId")
    List<Attendance> findMonthlyAttendanceByLevel(@Param("levelId") Long levelId,
                                                  @Param("startDate") LocalDateTime startDate,
                                                  @Param("endDate") LocalDateTime endDate);

}
