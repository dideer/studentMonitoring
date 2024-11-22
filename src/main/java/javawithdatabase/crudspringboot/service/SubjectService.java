package javawithdatabase.crudspringboot.service;

import javawithdatabase.crudspringboot.model.Level;
import javawithdatabase.crudspringboot.model.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getAllSubjects();
    Subject getSubjectById(String SubjectCode);
    Subject saveSubject(Subject subject);
    void deleteSubject(String SubjectCode);
    Subject updateSubject(Subject subject);



    List<Subject> getSubjectsByLevelId(Long levelId);
    List<Subject> findUnassignedSubjects();
}
