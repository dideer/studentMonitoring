package javawithdatabase.crudspringboot.repository;

import javawithdatabase.crudspringboot.model.Subject;
import javawithdatabase.crudspringboot.model.SubjectToTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SubjectToTeacherRepository extends JpaRepository<SubjectToTeacher, Integer> {


    @Query("SELECT s FROM SubjectToTeacher s WHERE s.teacher.teacherId = :teacherId")
    List<SubjectToTeacher> findByTeacherId(Long teacherId);


    @Query("SELECT s from SubjectToTeacher s WHERE s.subject.SubjectCode = :subjectCode")
    SubjectToTeacher findBySubjectCode(String subjectCode);




}
