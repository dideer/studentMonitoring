package javawithdatabase.crudspringboot.service.impl;

import javawithdatabase.crudspringboot.model.SubjectToTeacher;
import javawithdatabase.crudspringboot.repository.SubjectToTeacherRepository;
import javawithdatabase.crudspringboot.service.SubjectToTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectToTeacherServiceImpl implements SubjectToTeacherService {

    @Autowired
    private SubjectToTeacherRepository subjectToTeacherRepository;



    @Override
    public SubjectToTeacher saveSubjectToTeacher(SubjectToTeacher subjectToTeacher) {

        return subjectToTeacherRepository.save(subjectToTeacher) ;

    }

    @Override
    public List<SubjectToTeacher> findAllSubjectToTeachers() {
        return subjectToTeacherRepository.findAll();
    }

    @Override
    public List<SubjectToTeacher> findSubjectToTeacherById(Long teacherId) {
        return subjectToTeacherRepository.findByTeacherId(teacherId);
    }

    @Override
    public SubjectToTeacher findSubjectByCode(String subjectCode) {
        return subjectToTeacherRepository.findBySubjectCode(subjectCode);
    }

}
