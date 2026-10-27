package com.maintainx.controller;
import com.maintainx.model.MaintenanceSchedule;
import com.maintainx.service.MaintenanceScheduleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/maintenance-schedules")
public class MaintenanceScheduleController {
    private final MaintenanceScheduleService service;
    public MaintenanceScheduleController(MaintenanceScheduleService service) { this.service = service; }
    @GetMapping public List<MaintenanceSchedule> all() { return service.all(); }
    @GetMapping("/{id}") public MaintenanceSchedule get(@PathVariable Long id) { return service.get(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public MaintenanceSchedule create(@Valid @RequestBody MaintenanceSchedule item) { return service.create(item); }
    @PutMapping("/{id}") public MaintenanceSchedule update(@PathVariable Long id, @Valid @RequestBody MaintenanceSchedule item) { return service.update(id, item); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { service.delete(id); }
}
