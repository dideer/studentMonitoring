package javawithdatabase.crudspringboot.controller;

import javawithdatabase.crudspringboot.model.HoD;
import javawithdatabase.crudspringboot.model.User;
import javawithdatabase.crudspringboot.repository.HodRepository;
import javawithdatabase.crudspringboot.service.DepartmentService;
import javawithdatabase.crudspringboot.service.HodService;
import javawithdatabase.crudspringboot.service.LevelService;
import javawithdatabase.crudspringboot.service.sec.CustomerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class hodController {


    @Autowired
    private HodService hodService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private HodRepository hodRepository;

    @Autowired
    private UserDetailsService userDetailsService;


    @GetMapping("admin/hod")
    public String hodController(@ModelAttribute("hod") HoD hoD, Model model,Principal principal) {


        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());

        model.addAttribute("departments", departmentService.allDepartmentsByUse());

        model.addAttribute("hod", hodService.findAll());
        return "hod";


    }


    @PostMapping("admin/hod/save")
    public String hodSave(@ModelAttribute HoD hoD, Model model) {

        hodService.saveHoD(hoD);

        return"redirect:/admin/hod";

    }

    @GetMapping("/admin/hod/addUser/{hodId}")
    public String hodById(@PathVariable("hodId") Long hodId, Model model) {
        model.addAttribute("hods",hodService.findById(hodId));
        return "/registerHod";
    }




}
