package com.example.hasjobs.controller;

import com.example.hasjobs.entity.User;
import com.example.hasjobs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/login-user")
    public String login() {
        return "login-page";
    }

    @PostMapping("/register-user")
    public String saveUser(@ModelAttribute User user, @RequestParam("confirm-password") String confirmPassword, Model model) {
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null) {
            model.addAttribute("emailPresent", "This email Already Present, Please Try with Other email");
            return "registration-form";
        } else {
            if (userService.bothPasswordsSame(user.getPassword(), confirmPassword)) {
                User newUser = new User();
                newUser.setName(user.getName());
                newUser.setEmail(user.getEmail());
                newUser.setPassword(passwordEncoder.encode(user.getPassword()));
                newUser.setRole("USER");
                userService.saveTheUser(newUser);
            } else {
                model.addAttribute("passwordMissMatch", "Password is not same, please provide same password");
                return "registration-form";
            }
        }
        return "login-page";
    }
    @GetMapping(value = "/create-account")
    public String createAccount(){
        return "registration-form";
    }

    @GetMapping(value = "/edit-profile/{id}")
    public String editProfile(@PathVariable("id") int userId,
                              Model model,
                              Principal principal){
        User user=userService.findUserById(userId);
        model.addAttribute("user",user);
        return "show-user-profile";

    }

    @PostMapping(value = "/update-user")
    public String updateUser(@ModelAttribute("user")User user,
                             @RequestParam("userId")int userId,
                             Principal principal){
        User user1=userService.findUserById(userId);
        user1.setEmail(user.getEmail());
        System.out.println(user.getEmail());
        user1.setName(user.getName());
        userService.saveTheUser(user1);
        return "redirect:/";
    }
}
