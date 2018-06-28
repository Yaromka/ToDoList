package com.epam.todo.controller;

import com.epam.todo.entity.Role;
import com.epam.todo.entity.User;
import com.epam.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @GetMapping("/about")
    public String about(){return "about";}

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model){
        User userDb = userRepository.findByUsername(user.getUsername());
        if (userDb != null){
            model.put("message", "User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }
}
