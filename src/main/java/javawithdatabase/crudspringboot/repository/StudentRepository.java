package javawithdatabase.crudspringboot.repository;

import javawithdatabase.crudspringboot.model.Student;

import javawithdatabase.crudspringboot.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, String> {

    @Query("SELECT s FROM Student s WHERE s.level.LevelId = :levelId")
    List<Student> findByLevelId(@Param("levelId") Long levelId);


//    @Query("SELECT s.studentId FROM Student s WHERE s.studentId LIKE ?1 ORDER BY s.studentId DESC")
//    List<String> findLastStudentIdsByYear(String yearPrefix);

//    @Query("SELECT s.studentId FROM Student s WHERE s.studentId LIKE :yearPrefix ORDER BY s.studentId DESC")
//    List<String> findLastStudentIdsByYear(@Param("yearPrefix") String yearPrefix);

    @Query("SELECT s.studentId FROM Student s WHERE s.studentId LIKE :yearPrefix ORDER BY LENGTH(s.studentId) DESC, s.studentId DESC")
    List<String> findLastStudentIdsByYear(@Param("yearPrefix") String yearPrefix);


}
