package javawithdatabase.crudspringboot.controller;

import javawithdatabase.crudspringboot.model.User;
import javawithdatabase.crudspringboot.service.UserService;
import javawithdatabase.crudspringboot.service.sec.CustomerUserDetails;
import javawithdatabase.crudspringboot.service.sec.UserRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;



    @GetMapping("/login")
    public String login(){

            return "login";
    }

    @GetMapping("/register")
    public String register(){


        return "registerUser";
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        try {
            userService.registerUser(user);
            return "redirect:/login";
        } catch (UserRegistrationException e) {
            // Add the error message to the model to display it on the registration page
            model.addAttribute("errorMessage", e.getMessage());
            return "registerUser"; // Return to the registration page with the error message
        }
    }

    @GetMapping("/change-password")
    public String showChangePasswordForm(Model model, Principal principal) {

        UserDetails userDetailss = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("teachersUser", userDetailss);


        CustomerUserDetails user = (CustomerUserDetails) userDetailsService.loadUserByUsername(userDetailss.getUsername());

        User user1 = user.getUser();

        model.addAttribute("user", user1);

        model.addAttribute("userId", user1.getUserId());
        return "teacher/changePassword";  // Return to the change-password view
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



        return "/teacher/changePassword";
    }

}
