package com.live.ris.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.live.ris.entities.User;
import com.live.ris.repositories.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository UserRepository;

    // Show list of users
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", UserRepository.findAll());
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
        Optional<User> existingUserOpt = UserRepository.findByUserName(user.getUserName());

        // If username already exists for a different user
        if (existingUserOpt.isPresent() &&
                (user.getId() == null || !existingUserOpt.get().getId().equals(user.getId()))) {
            model.addAttribute("user", user);
            model.addAttribute("error", "Username already exists");
            return "user_entry";
        }

        if (user.getId() == null) {
            user.setCreateDate(LocalDateTime.now());
            user.setActive(true); // default to active on creation
        }
        user.setModifyDate(LocalDateTime.now());
        UserRepository.save(user);
        return "redirect:/users";
    }

    // Show form to edit existing user
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Optional<User> userOpt = UserRepository.findById(id);
        userOpt.ifPresent(user -> model.addAttribute("user", user));
        return "user_entry";
    }

    // Inactivate user instead of deleting
    @GetMapping("/toggle/{id}")
    public String toggleUserActiveStatus(@PathVariable("id") Long id) {
        Optional<User> userOpt = UserRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setActive(!Boolean.TRUE.equals(user.getActive())); 
            user.setModifyDate(LocalDateTime.now());
            UserRepository.save(user);
        }
        return "redirect:/users";
    }
}
