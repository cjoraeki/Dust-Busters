package com.cleaning.admin.repository;

import com.cleaning.admin.model.Cleaner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CleanerRepository extends JpaRepository<Cleaner, Long> {
    List<Cleaner> findByAvailable(boolean available);
}

