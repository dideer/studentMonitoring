package javawithdatabase.crudspringboot.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class SubjectToTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    @ManyToOne
    @JoinColumn(name = "SubjectCode" , nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacherId",nullable = false)
    private Teacher teacher;

    public SubjectToTeacher() {
        super();
    }

    public SubjectToTeacher(Subject subject, Teacher teacher) {
        this.subject = subject;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
