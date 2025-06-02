package com.live.ris.controllers;

import com.live.ris.entities.User;
import com.live.ris.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName,
                        @RequestParam String pass,
                        @RequestParam String role,
                        Model model) {
        User user = userService.authenticate(userName, pass);
        if (user != null && user.getRole().equalsIgnoreCase(role)) {
            model.addAttribute("user", user);
            switch (role.toLowerCase()) {
                case "admin":
                    return "dashboard_admin";
                case "doctor":
                    return "dashboard_doc";
                case "technician":
                    return "dashboard_tech";
                case "receptionist":
                    return "dashboard_reception";
                default:
                    model.addAttribute("error", "Invalid role specified.");
                    return "login";
            }
        } else {
            model.addAttribute("error", "Invalid credentials or role");
            return "login";
        }
    }
}
