package com.maintainx.service;
import com.maintainx.exception.ResourceNotFoundException;
import com.maintainx.model.MaintenanceSchedule;
import com.maintainx.repository.MaintenanceScheduleRepository;
import com.maintainx.repository.EquipmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MaintenanceScheduleService {
    private final MaintenanceScheduleRepository repository;
    private final EquipmentRepository equipmentRepository;
    public MaintenanceScheduleService(MaintenanceScheduleRepository repository, EquipmentRepository equipmentRepository) {
        this.repository = repository; this.equipmentRepository = equipmentRepository;
    }
    public List<MaintenanceSchedule> all() { return repository.findAll(); }
    public MaintenanceSchedule get(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Maintenance schedule not found: " + id)); }
    public MaintenanceSchedule create(MaintenanceSchedule item) {
        validateEquipment(item.getEquipmentId()); item.setId(null); return repository.save(item);
    }
    public MaintenanceSchedule update(Long id, MaintenanceSchedule input) {
        MaintenanceSchedule item = get(id); validateEquipment(input.getEquipmentId());
        item.setEquipmentId(input.getEquipmentId()); item.setTitle(input.getTitle());
        item.setFrequencyDays(input.getFrequencyDays()); item.setNextDueDate(input.getNextDueDate()); item.setNotes(input.getNotes());
        return repository.save(item);
    }
    public void delete(Long id) { repository.delete(get(id)); }
    private void validateEquipment(Long id) {
        if (!equipmentRepository.existsById(id)) throw new ResourceNotFoundException("Equipment not found: " + id);
    }
}
