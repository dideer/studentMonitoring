package javawithdatabase.crudspringboot.controller;

import javawithdatabase.crudspringboot.dto.DepartmentDto;
import javawithdatabase.crudspringboot.model.Department;
import javawithdatabase.crudspringboot.model.Level;
import javawithdatabase.crudspringboot.model.User;
import javawithdatabase.crudspringboot.service.DepartmentService;
import javawithdatabase.crudspringboot.service.LevelService;
import javawithdatabase.crudspringboot.service.sec.CustomerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class LevelController {


    @Autowired
    private LevelService levelService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/level/getLevel")
    public String getLevel(@ModelAttribute("level") Level level, Model model, Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());



        model.addAttribute("departments", departmentService.allDepartmentsByStatus("Active"));
        model.addAttribute("levels", levelService.allLevelByStatus("Active"));
        return "level";
    }

    @PostMapping("/level/addLevel")
    public String addLevel(@ModelAttribute("level") Level level,Model model) {
        level.setStatus("Active");
        levelService.saveLevel(level);
        model.addAttribute("message", "Thanks you for add level");
        return "redirect:/admin/level/getLevel";
    }

    @GetMapping("/level/edit/{levelId}")
    public String findLevelById(@PathVariable("levelId") Long levelId, Model model,Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());


        model.addAttribute("level", levelService.getLevelById(levelId));
        model.addAttribute("departments", departmentService.allDepartmentsByStatus("Active"));
        model.addAttribute("levels", levelService.getAllLevels());

        return "level";
    }

    @PostMapping("/updateLevel/{levelId}")
    public String updateLevel(@PathVariable("levelId") Long levelId,@ModelAttribute("level") Level level ,Model model) {

        Level oldLevel = levelService.getLevelById(levelId);



        oldLevel.setLevelId(levelId);

        oldLevel.setLevelName(level.getLevelName());
        oldLevel.setDepartment(level.getDepartment());


        levelService.saveLevel(oldLevel);


        model.addAttribute("message", "Thanks you for edit level");



        return "redirect:/admin/level/getLevel";
    }

    @GetMapping("/level/delete/{level_id}")
    public String deleteLevel(@PathVariable Long level_id) {

        levelService.deleteLevels(level_id);

        return "redirect:/admin/level/getLevel";
    }







}
