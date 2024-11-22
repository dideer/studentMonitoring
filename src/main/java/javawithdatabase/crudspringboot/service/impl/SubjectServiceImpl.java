package javawithdatabase.crudspringboot.service.impl;

import javawithdatabase.crudspringboot.model.Level;
import javawithdatabase.crudspringboot.model.Subject;
import javawithdatabase.crudspringboot.repository.SubjectRepository;
import javawithdatabase.crudspringboot.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(String SubjectCode) {
        return subjectRepository.findById(SubjectCode).get();
    }

    @Override
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public void deleteSubject(String SubjectCode) {
        subjectRepository.deleteById(SubjectCode);
    }

    @Override
    public Subject updateSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getSubjectsByLevelId(Long levelId) {
        return subjectRepository.findByLevelId(levelId);
    }

    @Override
    public List<Subject> findUnassignedSubjects() {
        return subjectRepository.findUnassignedSubjects();
    }


}
