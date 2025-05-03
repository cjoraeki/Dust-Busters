package com.cleaning.admin.controller;

import com.cleaning.admin.model.Task;
import com.cleaning.admin.service.CleanerService;
import com.cleaning.admin.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final CleanerService cleanerService;

    @Autowired
    public TaskController(TaskService taskService, CleanerService cleanerService) {
        this.taskService = taskService;
        this.cleanerService = cleanerService;
    }

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks/list";
    }

    @GetMapping("/new")
    public String newTaskForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("cleaners", cleanerService.getAllCleaners());
        model.addAttribute("statuses", Task.TaskStatus.values());
        return "tasks/form";
    }

    @PostMapping
    public String saveTask(@ModelAttribute Task task, RedirectAttributes redirectAttributes) {
        taskService.createTask(task);
        redirectAttributes.addFlashAttribute("success", "Task created successfully");
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/edit")
    public String editTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
        model.addAttribute("task", task);
        model.addAttribute("cleaners", cleanerService.getAllCleaners());
        model.addAttribute("statuses", Task.TaskStatus.values());
        return "tasks/form";
    }

    @PostMapping("/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task, RedirectAttributes redirectAttributes) {
        task.setId(id);
        taskService.updateTask(task);
        redirectAttributes.addFlashAttribute("success", "Task updated successfully");
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        taskService.deleteTask(id);
        redirectAttributes.addFlashAttribute("success", "Task deleted successfully");
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/assign")
    public String assignTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task Id:" + id));
        model.addAttribute("task", task);
        model.addAttribute("cleaners", cleanerService.getAvailableCleaners());
        return "tasks/assign";
    }

    @PostMapping("/{id}/assign")
    public String assignTask(@PathVariable Long id, @RequestParam Long cleanerId, RedirectAttributes redirectAttributes) {
        taskService.assignTaskToCleaner(id, cleanerId);
        redirectAttributes.addFlashAttribute("success", "Task assigned successfully");
        return "redirect:/tasks";
    }
}

