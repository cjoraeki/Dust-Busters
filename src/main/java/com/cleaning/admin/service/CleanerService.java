package com.cleaning.admin.service;

import com.cleaning.admin.model.Cleaner;
import com.cleaning.admin.repository.CleanerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CleanerService {

    private final CleanerRepository cleanerRepository;

    @Autowired
    public CleanerService(CleanerRepository cleanerRepository) {
        this.cleanerRepository = cleanerRepository;
    }

    public List<Cleaner> getAllCleaners() {
        return cleanerRepository.findAll();
    }

    public Optional<Cleaner> getCleanerById(Long id) {
        return cleanerRepository.findById(id);
    }

    public Cleaner createCleaner(Cleaner cleaner) {
        return cleanerRepository.save(cleaner);
    }

    public Cleaner updateCleaner(Cleaner cleaner) {
        return cleanerRepository.save(cleaner);
    }

    public void deleteCleaner(Long id) {
        cleanerRepository.deleteById(id);
    }

    public List<Cleaner> getAvailableCleaners() {
        return cleanerRepository.findByAvailable(true);
    }
}

