package javawithdatabase.crudspringboot.service.impl;

import javawithdatabase.crudspringboot.model.Department;
import javawithdatabase.crudspringboot.model.Level;
import javawithdatabase.crudspringboot.repository.LevelRepository;
import javawithdatabase.crudspringboot.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl implements LevelService {

    @Autowired
    private LevelRepository levelRepository;

    @Override
    public List<Level> getAllLevels() {


        return levelRepository.findAll();
    }

    @Override
    public Level getLevelById(Long levelId) {

        return levelRepository.findById(levelId).get();
    }

    @Override
    public Level saveLevel(Level level) {


        return levelRepository.save(level);
    }

    @Override
    public Level updateLevel(Level level) {


        return levelRepository.save(level);
    }


    @Override
    public List<Level> getAllLevelsByDepartment(Long departmentId) {

        return levelRepository.findLevelByDepartments(departmentId);
    }

    @Override
    public void deleteLevels(Long levelId) {
        Level level = levelRepository.findById(levelId).get();
        level.setStatus("DisActive");
        levelRepository.save(level);
    }

    @Override
    public List<Level> allLevelByStatus(String status) {
        return levelRepository.findByStatus(status);
    }
}
