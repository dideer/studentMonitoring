package javawithdatabase.crudspringboot.repository;

import javawithdatabase.crudspringboot.model.gateAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface GateAttendanceRepo extends JpaRepository<gateAttendance, Integer> {

    @Query("SELECT g FROM gateAttendance g " +
            "WHERE g.toDays = :toDays AND g.photoToStudent.student.level.LevelId = :levelId and g.status='Present'")
    List<gateAttendance> findTodayAttendanceByLevel(@Param("toDays") LocalDate toDays, @Param("levelId") Long levelId);

    @Query("SELECT g FROM gateAttendance g " +
            "WHERE g.toDays = :toDays AND g.photoToStudent.student.level.LevelId = :levelId")
    List<gateAttendance> findTodayAttendanceByLevels(@Param("toDays") LocalDate toDays, @Param("levelId") Long levelId);

    @Query("SELECT g FROM gateAttendance g " +
            "WHERE g.toDays BETWEEN :startDate AND :endDate AND g.photoToStudent.student.level.LevelId = :levelId")
    List<gateAttendance> findWeeklyAttendanceByLevel(@Param("startDate") LocalDate startDate,
                                                     @Param("endDate") LocalDate endDate,
                                                     @Param("levelId") Long levelId);

    @Query("SELECT g FROM gateAttendance g " +
            "WHERE g.toDays BETWEEN :startDate AND :endDate AND g.photoToStudent.student.level.LevelId = :levelId")
    List<gateAttendance> findMonthlyAttendanceByLevel(@Param("startDate") LocalDate startDate,
                                                     @Param("endDate") LocalDate endDate,
                                                     @Param("levelId") Long levelId);
}
