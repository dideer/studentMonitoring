package javawithdatabase.crudspringboot.dto;

public class DepartmentDto {

    private Long department_id;
    private String department_name;
    private String department_description;
    private String status;
    public DepartmentDto() {
        super();
    }

    public DepartmentDto(String department_name, String department_description,String status) {
        this.department_name = department_name;
        this.department_description = department_description;
        this.status = status;
    }

    public Long getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Long department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getDepartment_description() {
        return department_description;
    }

    public void setDepartment_description(String department_description) {
        this.department_description = department_description;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
