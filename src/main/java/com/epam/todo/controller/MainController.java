package com.epam.todo.controller;

import com.epam.todo.entity.Task;
import com.epam.todo.entity.User;
import com.epam.todo.repository.TaskRepository;
import com.epam.todo.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
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
            @Valid Task task,
            BindingResult bindingResult,
            Model model){
        task.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtil.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("task", task);
        } else {
            model.addAttribute("task", null);
            taskRepository.save(task);
        }
        Iterable<Task> allTasks = taskRepository.findByAuthor(user);
        model.addAttribute("tasks", allTasks);
        return "main";
    }
}