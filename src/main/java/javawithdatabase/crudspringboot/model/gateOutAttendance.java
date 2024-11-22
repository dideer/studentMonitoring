package javawithdatabase.crudspringboot.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "gate_out_attendance")
public class gateOutAttendance {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gate_attendance_id")
    private gateAttendance gateAttendance;

    private LocalDate date;

    private String status;

    private LocalTime outTime;

    public gateOutAttendance() {
        super();
    }

    public gateOutAttendance(gateAttendance gateAttendance, LocalDate date, String status) {
        this.gateAttendance = gateAttendance;
        this.date = date;
        this.status = status;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public gateAttendance getGateAttendance() {
        return gateAttendance;
    }
    public void setGateAttendance(gateAttendance gateAttendance) {
        this.gateAttendance = gateAttendance;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
}
