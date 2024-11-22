package javawithdatabase.crudspringboot.controller;

import javawithdatabase.crudspringboot.model.Level;
import javawithdatabase.crudspringboot.model.User;
import javawithdatabase.crudspringboot.service.LevelService;
import javawithdatabase.crudspringboot.service.sec.CustomerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/hod")
public class ControllerHod {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private LevelService levelService;

    @GetMapping("/")
    public String index(Model model, Principal principal){

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());




        return "hod/index";
    }

    @GetMapping("/report")
    public String report(Model model, Principal principal){

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());

        List<Level> levels = levelService.getAllLevelsByDepartment(user1.getHod().getDepartment().getDepartment_id()) ;

        model.addAttribute("levelByDepartment", levels);




        return "hod/classReport";
    }

    @GetMapping("/gateReport")
    public String gateReport(Model model, Principal principal){

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());

        List<Level> levels = levelService.getAllLevelsByDepartment(user1.getHod().getDepartment().getDepartment_id()) ;

        model.addAttribute("levelByDepartment", levels);




        return "hod/gateReport";
    }

    @GetMapping("/session/getLevel")
    public String getSession(Model model, Principal principal){

        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetails);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());

        List<Level> levels = levelService.getAllLevelsByDepartment(user1.getHod().getDepartment().getDepartment_id()) ;

        model.addAttribute("levelByDepartment", levels);




        return "hod/getSession";
    }



}
