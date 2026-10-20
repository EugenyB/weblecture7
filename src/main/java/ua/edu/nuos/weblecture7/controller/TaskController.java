package ua.edu.nuos.weblecture7.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.edu.nuos.weblecture7.model.Task;
import ua.edu.nuos.weblecture7.repository.TaskRepository;

@Controller
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    private TaskRepository repository;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("tasks", repository.findAll());
        return "tasks/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("task", repository.findById(id));
        return "tasks/detail";
    }

    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("task", new Task());
        return "tasks/form";
    }

    @PostMapping
    public String newTask(
            @Valid @ModelAttribute("task") Task task,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            return "tasks/form";
        }
        repository.save(task);
        return "redirect:/tasks/list";
    }
}
