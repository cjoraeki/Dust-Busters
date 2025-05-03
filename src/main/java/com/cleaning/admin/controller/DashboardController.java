package com.cleaning.admin.controller;

import com.cleaning.admin.model.Task;
import com.cleaning.admin.service.CleanerService;
import com.cleaning.admin.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final TaskService taskService;
    private final CleanerService cleanerService;

    @Autowired
    public DashboardController(TaskService taskService, CleanerService cleanerService) {
        this.taskService = taskService;
        this.cleanerService = cleanerService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("pendingTasks", taskService.getTasksByStatus(Task.TaskStatus.PENDING));
        model.addAttribute("inProgressTasks", taskService.getTasksByStatus(Task.TaskStatus.IN_PROGRESS));
        model.addAttribute("completedTasks", taskService.getTasksByStatus(Task.TaskStatus.COMPLETED));
        model.addAttribute("todayTasks", taskService.getTasksForToday());
        model.addAttribute("availableCleaners", cleanerService.getAvailableCleaners());
        model.addAttribute("totalCleaners", cleanerService.getAllCleaners().size());
        return "dashboard";
    }
}

