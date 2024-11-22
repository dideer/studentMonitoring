package javawithdatabase.crudspringboot.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "gate_attendance")
public class gateAttendance {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate toDays;

    @ManyToOne
    @JoinColumn(name = "studentId")
    private photoToStudent photoToStudent;
    private  String status;
    private LocalTime enterTime;

    public gateAttendance() {
        super();
    }

    public gateAttendance(LocalDate toDays, photoToStudent photoToStudent, String status,LocalTime enterTime) {
        this.toDays = toDays;
        this.photoToStudent = photoToStudent;
        this.status = status;
        this.enterTime = enterTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getToDays() {
        return toDays;
    }

    public void setToDays(LocalDate toDays) {
        this.toDays = toDays;
    }

    public photoToStudent getPhotoToStudent() {
        return photoToStudent;
    }

    public void setPhotoToStudent(photoToStudent photoToStudent) {
        this.photoToStudent = photoToStudent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public LocalTime getEnterTime() {
        return enterTime;
    }
    public void setEnterTime(LocalTime enterTime) {
        this.enterTime = enterTime;
    }
}
