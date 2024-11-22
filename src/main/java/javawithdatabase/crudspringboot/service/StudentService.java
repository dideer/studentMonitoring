package javawithdatabase.crudspringboot.service;

import javawithdatabase.crudspringboot.model.Student;
import javawithdatabase.crudspringboot.model.Subject;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);
    Student getStudentById(String StudentId);
    List<Student> getAllStudents();
    List<Student> getStudentsByLevelId(Long levelId);


    String generateNewStudentId();
}
