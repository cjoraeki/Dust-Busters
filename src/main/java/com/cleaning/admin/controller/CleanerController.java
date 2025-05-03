package com.cleaning.admin.controller;

import com.cleaning.admin.model.Cleaner;
import com.cleaning.admin.service.CleanerService;
import com.cleaning.admin.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cleaners")
public class CleanerController {

    private final CleanerService cleanerService;
    private final TaskService taskService;

    @Autowired
    public CleanerController(CleanerService cleanerService, TaskService taskService) {
        this.cleanerService = cleanerService;
        this.taskService = taskService;
    }

    @GetMapping
    public String listCleaners(Model model) {
        model.addAttribute("cleaners", cleanerService.getAllCleaners());
        return "cleaners/list";
    }

    @GetMapping("/new")
    public String newCleanerForm(Model model) {
        model.addAttribute("cleaner", new Cleaner());
        return "cleaners/form";
    }

    @PostMapping
    public String saveCleaner(@ModelAttribute Cleaner cleaner, RedirectAttributes redirectAttributes) {
        cleanerService.createCleaner(cleaner);
        redirectAttributes.addFlashAttribute("success", "Cleaner created successfully");
        return "redirect:/cleaners";
    }

    @GetMapping("/{id}/edit")
    public String editCleanerForm(@PathVariable Long id, Model model) {
        Cleaner cleaner = cleanerService.getCleanerById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cleaner Id:" + id));
        model.addAttribute("cleaner", cleaner);
        return "cleaners/form";
    }

    @PostMapping("/{id}")
    public String updateCleaner(@PathVariable Long id, @ModelAttribute Cleaner cleaner, RedirectAttributes redirectAttributes) {
        cleaner.setId(id);
        cleanerService.updateCleaner(cleaner);
        redirectAttributes.addFlashAttribute("success", "Cleaner updated successfully");
        return "redirect:/cleaners";
    }

    @GetMapping("/{id}/delete")
    public String deleteCleaner(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        cleanerService.deleteCleaner(id);
        redirectAttributes.addFlashAttribute("success", "Cleaner deleted successfully");
        return "redirect:/cleaners";
    }

    @GetMapping("/{id}/tasks")
    public String viewCleanerTasks(@PathVariable Long id, Model model) {
        Cleaner cleaner = cleanerService.getCleanerById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cleaner Id:" + id));
        model.addAttribute("cleaner", cleaner);
        model.addAttribute("tasks", taskService.getTasksByCleanerId(id));
        return "cleaners/tasks";
    }
}

