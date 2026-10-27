package com.maintainx.controller;
import com.maintainx.model.*;
import com.maintainx.service.WorkOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/work-orders")
public class WorkOrderController {
    private final WorkOrderService service;
    public WorkOrderController(WorkOrderService service) { this.service = service; }
    @GetMapping public List<WorkOrder> all(@RequestParam(required=false) WorkOrderStatus status,
        @RequestParam(required=false) Priority priority) { return service.all(status, priority); }
    @GetMapping("/{id}") public WorkOrder get(@PathVariable Long id) { return service.get(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public WorkOrder create(@Valid @RequestBody WorkOrder item) { return service.create(item); }
    @PutMapping("/{id}") public WorkOrder update(@PathVariable Long id, @Valid @RequestBody WorkOrder item) { return service.update(id, item); }
    @PatchMapping("/{id}/status") public WorkOrder updateStatus(@PathVariable Long id, @RequestParam WorkOrderStatus status) { return service.updateStatus(id, status); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { service.delete(id); }
}
