package com.live.ris.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.live.ris.entities.User;
import com.live.ris.repositories.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Show list of users
//    @GetMapping
//    public String listUsers(Model model) {
//        model.addAttribute("users", userRepository.findAll());
//        return "users_list";
//    }
    
    @GetMapping
    public String listUsers(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<User> users;

        if (keyword != null && !keyword.trim().isEmpty()) {
            users = userRepository.findByFullNameContainingIgnoreCaseOrPhoneContaining(keyword, keyword);
        } else {
            users = userRepository.findAll();
        }

        model.addAttribute("users", users);
        model.addAttribute("keyword", keyword);
        return "users_list";
    }


    // Show form to create a new user
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "user_entry";
    }

    // Save new or updated user
    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        // Check if username is already taken by another user
        Optional<User> existingUserByUsername = userRepository.findByUserName(user.getUserName());

        if (existingUserByUsername.isPresent() &&
                (user.getId() == null || !existingUserByUsername.get().getId().equals(user.getId()))) {
            model.addAttribute("user", user);
            model.addAttribute("error", "Username already exists");
            return "user_entry";
        }

        // Handle create or update
        if (user.getId() == null) {
            // New user
            user.setCreateDate(LocalDateTime.now());
            user.setActive(true);
            
            // Hash password if provided
            if (user.getPass() != null && !user.getPass().trim().isEmpty()) {
                user.setPass(user.getPass());
            } else {
                model.addAttribute("user", user);
                model.addAttribute("error", "Password is required for new users.");
                return "user_entry";
            }

        } else {
            // Existing user — load current data
            Optional<User> existingUserOpt = userRepository.findById(user.getId());
            if (existingUserOpt.isPresent()) {
                User existingUser = existingUserOpt.get();

                // Preserve createDate and active
                user.setCreateDate(existingUser.getCreateDate());
                user.setActive(existingUser.getActive());

                // Only update password if new password is entered
                if (user.getPass() != null && !user.getPass().trim().isEmpty()) {
                    user.setPass(user.getPass());
                } else {
                    user.setPass(existingUser.getPass()); // reuse old password
                }
            }
        }

        user.setModifyDate(LocalDateTime.now());
        userRepository.save(user);
        return "redirect:/users";
    }

    // Show form to edit existing user
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<User> userOpt = userRepository.findById(id);
        userOpt.ifPresent(user -> model.addAttribute("user", user));
        return "user_entry";
    }

    // Inactivate user instead of deleting
    @GetMapping("/toggle/{id}")
    public String toggleUserActiveStatus(@PathVariable("id") Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setActive(!Boolean.TRUE.equals(user.getActive())); 
            user.setModifyDate(LocalDateTime.now());
            userRepository.save(user);
        }
        return "redirect:/users";
    }
}
