package com.cleaning.admin.service;

import com.cleaning.admin.model.Cleaner;
import com.cleaning.admin.model.Task;
import com.cleaning.admin.repository.CleanerRepository;
import com.cleaning.admin.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final CleanerRepository cleanerRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, CleanerRepository cleanerRepository) {
        this.taskRepository = taskRepository;
        this.cleanerRepository = cleanerRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        // Set default status for new tasks
        if (task.getStatus() == null) {
            task.setStatus(Task.TaskStatus.PENDING);
        }
        return taskRepository.save(task);
    }

    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task assignTaskToCleaner(Long taskId, Long cleanerId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Cleaner cleaner = cleanerRepository.findById(cleanerId)
                .orElseThrow(() -> new RuntimeException("Cleaner not found"));

        task.setAssignedCleaner(cleaner);
        return taskRepository.save(task);
    }

    public List<Task> getTasksByStatus(Task.TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> getTasksByCleanerId(Long cleanerId) {
        return taskRepository.findByAssignedCleanerId(cleanerId);
    }

    public List<Task> getTasksForToday() {
        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endOfDay = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        return taskRepository.findByScheduledTimeBetween(startOfDay, endOfDay);
    }
}

