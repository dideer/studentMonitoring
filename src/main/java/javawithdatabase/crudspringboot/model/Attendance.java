    package javawithdatabase.crudspringboot.model;


    import jakarta.persistence.*;

    import java.time.LocalDate;
    import java.time.LocalDateTime;

    @Entity
    @Table(name = "attendance")
    public class Attendance {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int attendanceId;

        @ManyToOne
        @JoinColumn(name = "session_id")
        private TeacherSession teacherSession;

        @ManyToOne
        @JoinColumn(name = "student_id")
        private Student student;

        private String status;

        private String duration;

        private LocalDateTime timestamp;

        public Attendance() {
            super();
        }

        public Attendance(TeacherSession teacherSession, Student student, String status, String duration, LocalDateTime timestamp) {
            this.teacherSession = teacherSession;
            this.student = student;
            this.status = status;
            this.duration = duration;
            this.timestamp = timestamp;
        }

        public int getAttendanceId() {
            return attendanceId;
        }

        public void setAttendanceId(int attendanceId) {
            this.attendanceId = attendanceId;
        }

        public TeacherSession getTeacherSession() {
            return teacherSession;
        }

        public void setTeacherSession(TeacherSession teacherSession) {
            this.teacherSession = teacherSession;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
        }
    }
