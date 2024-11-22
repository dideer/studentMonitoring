package javawithdatabase.crudspringboot.service;

import javawithdatabase.crudspringboot.model.SubjectToTeacher;

import java.util.List;

public interface SubjectToTeacherService {

        SubjectToTeacher saveSubjectToTeacher(SubjectToTeacher subjectToTeacher);
        List<SubjectToTeacher> findAllSubjectToTeachers();
        List<SubjectToTeacher> findSubjectToTeacherById(Long teacherId);
        SubjectToTeacher findSubjectByCode(String subjectCode);

}
