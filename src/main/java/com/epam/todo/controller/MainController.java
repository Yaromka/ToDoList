package com.epam.todo.controller;

import com.epam.todo.entity.Task;
import com.epam.todo.entity.User;
import com.epam.todo.repository.TaskRepository;
import com.epam.todo.util.ControllerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;
import java.util.Set;

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
            tasks = taskRepository.findByTag(tag);
        } else {
            tasks = taskRepository.findAll();
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

    @GetMapping("/user-tasks/{user}")
    public String userTasks(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Task task
    ){
        Set<Task> tasks = user.getTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("task", task);
        model.addAttribute("isCurrentUser", currentUser.getId().equals(user.getId()));

        return "userTasks";
    }
    //TODO Don't work task edit. Methods userTasks and updateTask should be located in another class TaskController.
    @PostMapping("/user-tasks/{user}")
    public String updateTask(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Task task,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag
    ){
        if(task.getAuthor().getId().equals(currentUser.getId())){
            if(!StringUtils.isEmpty(description)){
                task.setDescription(description);
            }

            if (!StringUtils.isEmpty(tag)){
                task.setTag(tag);
            }

            taskRepository.save(task);
        }
        return "redirect:/user-tasks/" + user;
    }
}