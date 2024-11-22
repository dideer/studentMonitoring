package javawithdatabase.crudspringboot.service;

import javawithdatabase.crudspringboot.model.Student;
import javawithdatabase.crudspringboot.model.photoToStudent;
import javawithdatabase.crudspringboot.repository.PhotoToStudentResp;

import java.util.List;

public interface PhotoToStudentService {

    List<photoToStudent> getPhotoToStudents();
    List<photoToStudent> getStudentsByLevelId(Long levelId);


}
