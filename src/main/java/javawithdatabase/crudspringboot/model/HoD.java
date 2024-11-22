package javawithdatabase.crudspringboot.model;

import jakarta.persistence.*;

@Entity
@Table(name = "hod")
public class HoD {





    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long hodId;
    private String hodName;

    @OneToOne
    @JoinColumn(name = "department_id")
    private Department department;

    public HoD() {
        super();
    }

    public HoD(String hodName, Department department) {
        this.hodName = hodName;
        this.department = department;
    }


    public Long getHodId() {
        return hodId;
    }

    public void setHodId(Long hodId) {
        this.hodId = hodId;
    }

    public String getHodName() {
        return hodName;
    }

    public void setHodName(String hodName) {
        this.hodName = hodName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
