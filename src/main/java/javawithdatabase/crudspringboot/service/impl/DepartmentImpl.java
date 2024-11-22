package javawithdatabase.crudspringboot.service.impl;

import javawithdatabase.crudspringboot.dto.DepartmentDto;
import javawithdatabase.crudspringboot.model.Department;
import javawithdatabase.crudspringboot.repository.DepartmentRepository;
import javawithdatabase.crudspringboot.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public Department save(DepartmentDto departmentDto) {



        Department department = new Department(departmentDto.getDepartment_name(), departmentDto.getDepartment_description(),departmentDto.getStatus());

        return departmentRepository.save(department);
    }

    @Override
    public List<Department> AllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long department_id) {
        return departmentRepository.findById(department_id).get();
    }

    @Override
    public Department updateDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartments(Long department_id) {
        Department department = departmentRepository.findById(department_id).get();
        department.setStatus("DisActive");
        departmentRepository.save(department);
    }

    @Override
    public List<Department> allDepartmentsByStatus(String status) {
        return departmentRepository.findByStatus(status);
    }

    @Override
    public List<Department> allDepartmentsByUse() {
        return departmentRepository.departmentToUser();
    }


}
