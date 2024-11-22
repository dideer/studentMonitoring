package javawithdatabase.crudspringboot.model;


import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;

    private String password;

    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacherId", nullable = true)
    private Teacher teacher;



    @OneToOne
    @JoinColumn(name = "hod_id", nullable = true)
    private HoD hod;

    //The teacher field in User will be null for non-teachers



    public User() {
        super();
    }
    public User( String username, String password, String role,Teacher teacher,HoD hod) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.teacher = teacher;
        this.hod = hod;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;

    }
    public void setRole(String role) {
        this.role = role;
    }
    public Teacher getTeacher() {
        return teacher;
    }
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    public HoD getHod() {
        return hod;
    }
    public void setHod(HoD hod) {
        this.hod = hod;
    }
}
