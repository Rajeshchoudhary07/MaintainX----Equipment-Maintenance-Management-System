package com.maintainx.service;

import com.maintainx.exception.ResourceNotFoundException;
import com.maintainx.model.*;
import com.maintainx.repository.EquipmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EquipmentService {
    private final EquipmentRepository repository;
    public EquipmentService(EquipmentRepository repository) { this.repository = repository; }
    public List<Equipment> all(String search, EquipmentStatus status) {
        if (status != null) return repository.findByStatus(status);
        if (search != null && !search.isBlank())
            return repository.findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(search, search);
        return repository.findAll();
    }
    public Equipment get(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Equipment not found: " + id)); }
    public Equipment create(Equipment item) { item.setId(null); return repository.save(item); }
    public Equipment update(Long id, Equipment input) {
        Equipment item = get(id);
        item.setName(input.getName()); item.setCategory(input.getCategory());
        item.setLocation(input.getLocation()); item.setSerialNumber(input.getSerialNumber());
        item.setStatus(input.getStatus()); item.setPurchaseDate(input.getPurchaseDate());
        return repository.save(item);
    }
    public void delete(Long id) { repository.delete(get(id)); }
}
