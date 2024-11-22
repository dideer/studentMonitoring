package javawithdatabase.crudspringboot.service;

import javawithdatabase.crudspringboot.model.Teacher;

import java.util.List;

public interface TeacherService {

    Teacher createTeacher(Teacher teacher);
    Teacher updateTeacher(Teacher teacher);
    List<Teacher> getAllTeachers();
    Teacher getTeacherById(Long teacherId);
    void deleteTeacher(Long teacherId);
}
