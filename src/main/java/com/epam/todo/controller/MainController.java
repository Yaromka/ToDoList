package com.epam.todo.controller;

import com.epam.todo.entity.Task;
import com.epam.todo.entity.User;
import com.epam.todo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam(required = false) String tag,
            @AuthenticationPrincipal User user, Model model){
        Iterable<Task> tasks;

        if (tag != null && !tag.isEmpty()) {
            tasks = taskRepository.findByTagAndAuthor(tag, user);
        } else {
            tasks = taskRepository.findByAuthor(user);
        }

        model.addAttribute("tasks", tasks);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model){

        Task task = new Task(text, tag, user);
        taskRepository.save(task);
        Iterable<Task> allTasks = taskRepository.findByAuthor(user);
        model.put("tasks", allTasks);
        return "main";
    }
}