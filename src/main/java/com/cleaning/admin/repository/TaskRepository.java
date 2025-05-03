package com.cleaning.admin.repository;

import com.cleaning.admin.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Task.TaskStatus status);
    List<Task> findByAssignedCleanerId(Long cleanerId);
    List<Task> findByScheduledTimeBetween(LocalDateTime start, LocalDateTime end);
}

