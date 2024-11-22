package javawithdatabase.crudspringboot.repository;

import javawithdatabase.crudspringboot.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    List<Department> findByStatus(String status);


    @Query("SELECT d FROM Department d WHERE d.department_id NOT IN (SELECT h.department.department_id FROM HoD h) and d.status='Active'")
    List<Department> departmentToUser();

}
