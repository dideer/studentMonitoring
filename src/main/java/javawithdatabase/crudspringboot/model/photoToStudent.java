package javawithdatabase.crudspringboot.model;

import jakarta.persistence.*;

import java.sql.Blob;

@Entity
@Table(name = "photo_to_student")
public class photoToStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob()
    private Blob Image;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentId")
    private Student student;


    public photoToStudent() {
        super();
    }
    public photoToStudent(Blob Image, Student student) {
        this.Image = Image;
        this.student = student;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Blob getPhoto() {
        return Image;
    }
    public void setPhoto(Blob Image) {
        this.Image = Image;
    }
    public Student getStudent() {
        return student;
    }

}
