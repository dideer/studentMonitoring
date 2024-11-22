package javawithdatabase.crudspringboot.service.impl;

import javawithdatabase.crudspringboot.model.Student;
import javawithdatabase.crudspringboot.repository.PhotoToStudentResp;
import javawithdatabase.crudspringboot.repository.StudentRepository;
import javawithdatabase.crudspringboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PhotoToStudentResp PhotoToStudentResp;



//    @Override
//    public Student saveStudent(Student student) {
//
//        String studentId = generateNewStudentId();
//        student.setStudentId(studentId);
//        return studentRepository.save(student);
//    }
//@Override
//public Student saveStudent(Student student) {
//    // Generate a new ID only if the student doesn't already have one
//    if (student.getStudentId() == null || student.getStudentId().isEmpty()) {
//        String studentId = generateNewStudentId();
//        student.setStudentId(studentId);
//    }
//    return studentRepository.save(student);
//}

    @Override
    public Student saveStudent(Student student) {
        // Check if the student already exists
        if (student.getStudentId() == null || student.getStudentId().isEmpty()) {
            // Generate a new student ID for new students
            String studentId = generateNewStudentId();
            student.setStudentId(studentId);
        }
        // Save the student (insert or update)
        return studentRepository.save(student);
    }


    @Override
    public Student getStudentById(String StudentId) {
        return studentRepository.findById(StudentId).get();
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudentsByLevelId(Long levelId) {
        return studentRepository.findByLevelId(levelId);
    }


//    @Override
//    public String generateNewStudentId() {
//        String currentYear = String.valueOf(java.time.LocalDate.now().getYear());
//        String yearPrefix = currentYear + "STU%";
//        List<String> lastStudentIds = studentRepository.findLastStudentIdsByYear(yearPrefix);
//
//        String newStudentId;
//
//        if (!lastStudentIds.isEmpty()) {
//            // Get the first studentId (latest due to ordering)
//            String lastStudentId = lastStudentIds.get(0);
//            // Extract the numeric part and increment it
//            String numericPart = lastStudentId.substring(currentYear.length() + 3); // 4 for "STU"
//            int newIdNumber = Integer.parseInt(numericPart) + 1;
//            newStudentId = currentYear + "STU" + newIdNumber;
//        } else {
//            // First student for this year
//            newStudentId = currentYear + "STU1";
//        }
//
//        return newStudentId;
//    }

    @Override
    public String generateNewStudentId() {
        String currentYear = String.valueOf(java.time.LocalDate.now().getYear());
        String yearPrefix = currentYear + "STU%";

        // Fetch the last student IDs for the current year
        List<String> lastStudentIds = studentRepository.findLastStudentIdsByYear(yearPrefix);

        String newStudentId;

        if (!lastStudentIds.isEmpty()) {
            // Get the latest student ID
            String lastStudentId = lastStudentIds.get(0);
            // Use regex to extract the numeric part
            String numericPart = lastStudentId.replaceAll("^\\d{4}STU", "");
            // Parse the numeric part and increment
            int newIdNumber = Integer.parseInt(numericPart) + 1;
            newStudentId = currentYear + "STU" + newIdNumber;
        } else {
            // First student for this year
            newStudentId = currentYear + "STU1";
        }

        return newStudentId;
    }

}
