package javawithdatabase.crudspringboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "level")
public class Level {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long LevelId;

    private String LevelName;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department Department;

    private String status;

    public Level(String levelName, javawithdatabase.crudspringboot.model.Department department, String status) {
        LevelName = levelName;
        Department = department;
        this.status = status;
    }


    public Level() {
        super();
    }

    public Long getLevelId() {
        return LevelId;
    }

    public void setLevelId(Long levelId) {
        LevelId = levelId;
    }

    public String getLevelName() {
        return LevelName;
    }

    public void setLevelName(String levelName) {
        LevelName = levelName;
    }

    public Department getDepartment() {
        return Department;
    }
    public void setDepartment(Department department) {
        Department = department;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


}
