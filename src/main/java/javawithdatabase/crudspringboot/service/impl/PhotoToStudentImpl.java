package javawithdatabase.crudspringboot.service.impl;

import javawithdatabase.crudspringboot.model.Student;
import javawithdatabase.crudspringboot.model.photoToStudent;
import javawithdatabase.crudspringboot.repository.PhotoToStudentResp;
import javawithdatabase.crudspringboot.service.PhotoToStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoToStudentImpl implements PhotoToStudentService {

    @Autowired
    PhotoToStudentResp photoToStudentResp;

    @Override
    public List<photoToStudent> getPhotoToStudents() {
        return photoToStudentResp.findAll();
    }

    @Override
    public List<photoToStudent> getStudentsByLevelId(Long levelId) {
        return photoToStudentResp.findByLevelId(levelId);
    }
}
