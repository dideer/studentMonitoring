package javawithdatabase.crudspringboot.service;

import javawithdatabase.crudspringboot.model.Department;
import javawithdatabase.crudspringboot.model.Level;

import java.util.List;

public interface LevelService {
     List<Level> getAllLevels();
     Level getLevelById(Long levelId);
     Level saveLevel(Level level);
     Level updateLevel(Level level);
     List<Level> getAllLevelsByDepartment(Long departmentId);
     void deleteLevels(Long levelId);
     List<Level> allLevelByStatus(String status);



}
