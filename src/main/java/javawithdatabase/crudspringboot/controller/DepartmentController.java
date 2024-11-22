package javawithdatabase.crudspringboot.controller;

import javawithdatabase.crudspringboot.dto.DepartmentDto;
import javawithdatabase.crudspringboot.model.Department;
import javawithdatabase.crudspringboot.model.User;
import javawithdatabase.crudspringboot.service.DepartmentService;
import javawithdatabase.crudspringboot.service.sec.CustomerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserDetailsService userDetailsService;

//    @GetMapping("/addDepartment")
//    public String addDepartments(@ModelAttribute("department") DepartmentDto departmentDto,Model model) {
//        model.addAttribute("departmentss", departmentService.AllDepartments());
//        return "addDepartment";
//    }

    @GetMapping("/addDepartment")
    public String addDepartments(@ModelAttribute("department") DepartmentDto departmentDto, Model model, Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());


        model.addAttribute("departmentss", departmentService.allDepartmentsByStatus("Active"));
        return "addDepartment";
    }

    @GetMapping("/")
    public String Admin(Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());

        return "admin/index";
    }


    @PostMapping("/addDepartments")
    public String addDepartment(@ModelAttribute("department") DepartmentDto department, Model model) {
        department.setStatus("Active");
        departmentService.save(department);
        model.addAttribute("message", "Department added successfully");
        return "redirect:/admin/addDepartment";
    }

    @GetMapping("/department/edit/{department_id}")
    public String editDepartment(@PathVariable("department_id") Long departmentId, Model model,Principal principal) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());

        model.addAttribute("department",departmentService.getDepartmentById(departmentId));
        model.addAttribute("departmentss", departmentService.allDepartmentsByStatus("Active"));

        return "addDepartment";

    }

    @PostMapping("/updateDepartment/{department_id}")
    public String updateDepartment(@PathVariable("department_id") Long department_id,@ModelAttribute("department") DepartmentDto departmentDto, Model model) {

        Department existingDepartment = departmentService.getDepartmentById(department_id);

        existingDepartment.setDepartment_id(department_id);
        existingDepartment.setDepartment_name(departmentDto.getDepartment_name());
        existingDepartment.setDepartment_description(departmentDto.getDepartment_description());

        departmentService.updateDepartment(existingDepartment);

        return "redirect:/admin/addDepartment";

    }

    @GetMapping("/department/{department_id}")
    public String deleteDepartment(@PathVariable Long department_id) {

       departmentService.deleteDepartments(department_id);

        return "redirect:/admin/addDepartment";
    }



}
