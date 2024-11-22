package javawithdatabase.crudspringboot.repository;

import javawithdatabase.crudspringboot.model.Department;
import javawithdatabase.crudspringboot.model.Level;
import javawithdatabase.crudspringboot.model.gateAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface LevelRepository extends JpaRepository<Level, Long> {


    @Query("SELECT l FROM Level l " +
            "WHERE l.Department.department_id=:department_id and l.status='Active'")
    List<Level> findLevelByDepartments(@Param("department_id") Long department_id);


    List<Level> findByStatus(String status);

}
