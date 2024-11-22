package javawithdatabase.crudspringboot.service;

import javawithdatabase.crudspringboot.dto.DepartmentDto;
import javawithdatabase.crudspringboot.model.Department;

import java.util.List;

public interface DepartmentService {

    Department save(DepartmentDto departmentDto);

    List<Department> AllDepartments();

    Department getDepartmentById(Long department_id);

    Department updateDepartment(Department department);

    void deleteDepartments(Long department_id);

    List<Department> allDepartmentsByStatus(String status);

    List<Department> allDepartmentsByUse();

}
