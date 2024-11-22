package javawithdatabase.crudspringboot.repository;

import javawithdatabase.crudspringboot.model.Student;
import javawithdatabase.crudspringboot.model.photoToStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhotoToStudentResp extends JpaRepository<photoToStudent,Integer> {

    @Query("SELECT p FROM photoToStudent  p WHERE p.student.level.LevelId = :levelId")
    List<photoToStudent> findByLevelId(@Param("levelId") Long levelId);

}
