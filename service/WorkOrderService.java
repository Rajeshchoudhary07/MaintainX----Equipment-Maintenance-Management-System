package com.maintainx.service;
import com.maintainx.exception.ResourceNotFoundException;
import com.maintainx.model.*;
import com.maintainx.repository.WorkOrderRepository;
import com.maintainx.repository.EquipmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class WorkOrderService {
    private final WorkOrderRepository repository;
    private final EquipmentRepository equipmentRepository;
    public WorkOrderService(WorkOrderRepository repository, EquipmentRepository equipmentRepository) {
        this.repository = repository; this.equipmentRepository = equipmentRepository;
    }
    public List<WorkOrder> all(WorkOrderStatus status, Priority priority) {
        if (status != null) return repository.findByStatus(status);
        if (priority != null) return repository.findByPriority(priority);
        return repository.findAll();
    }
    public WorkOrder get(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Work order not found: " + id)); }
    public WorkOrder create(WorkOrder item) { validateEquipment(item.getEquipmentId()); item.setId(null); return repository.save(item); }
    public WorkOrder update(Long id, WorkOrder input) {
        WorkOrder item = get(id); validateEquipment(input.getEquipmentId());
        item.setEquipmentId(input.getEquipmentId()); item.setTitle(input.getTitle());
        item.setDescription(input.getDescription()); item.setPriority(input.getPriority());
        item.setStatus(input.getStatus()); item.setAssignedTo(input.getAssignedTo()); item.setDueDate(input.getDueDate());
        return repository.save(item);
    }
    public WorkOrder updateStatus(Long id, WorkOrderStatus status) {
        WorkOrder item = get(id); item.setStatus(status); return repository.save(item);
    }
    public void delete(Long id) { repository.delete(get(id)); }
    private void validateEquipment(Long id) {
        if (!equipmentRepository.existsById(id)) throw new ResourceNotFoundException("Equipment not found: " + id);
    }
}
