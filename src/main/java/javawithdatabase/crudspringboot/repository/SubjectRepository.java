package javawithdatabase.crudspringboot.repository;

import javawithdatabase.crudspringboot.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {



        @Query("SELECT s FROM Subject s WHERE s.level.LevelId = :levelId")
        List<Subject> findByLevelId(@Param("levelId") Long levelId);


        // Find all subjects that are not assigned to any teacher
        @Query("SELECT s FROM Subject s WHERE s.SubjectCode NOT IN (SELECT st.subject.SubjectCode FROM SubjectToTeacher st)")
        List<Subject> findUnassignedSubjects();

}
