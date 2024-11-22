package javawithdatabase.crudspringboot.controller;

import javawithdatabase.crudspringboot.model.User;
import javawithdatabase.crudspringboot.service.UserService;
import javawithdatabase.crudspringboot.service.sec.CustomerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/admins")
public class AdminController {


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;


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


    @GetMapping("/change-password")
    public String showChangePasswordForm(Model model, Principal principal) {

        UserDetails userDetailss = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetailss);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetailss.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());
        return "/admin/changePasswordAdmin";  // Return to the change-password view
    }

    @PostMapping("/change-passwords")
    public String changePassword(@RequestParam("userId") Long userId,
                                 @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 Model model,Principal principal) {
        User user = userService.findById(userId);
        if (user != null) {
            if (userService.checkOldPassword(user, oldPassword)) {
                userService.changePassword(user, newPassword);
                model.addAttribute("success", "Password changed successfully.");
            } else {
                model.addAttribute("message", "Old password is incorrect.");
            }
        } else {
            model.addAttribute("message", "User not found.");
        }
        model.addAttribute("userId", userId);  // Add userId to model





        UserDetails userDetailss = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetailss);


        CustomerUserDetails user2 = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetailss.getUsername());

        User user1 = user2.getUser();

        model.addAttribute("user", user1);



        return "/admin/changePasswordAdmin";
    }



}
