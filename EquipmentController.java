package com.maintainx.controller;
import com.maintainx.model.Equipment;
import com.maintainx.model.EquipmentStatus;
import com.maintainx.service.EquipmentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    private final EquipmentService service;
    public EquipmentController(EquipmentService service) { this.service = service; }
    @GetMapping public List<Equipment> all(@RequestParam(required=false) String search,
        @RequestParam(required=false) EquipmentStatus status) { return service.all(search, status); }
    @GetMapping("/{id}") public Equipment get(@PathVariable Long id) { return service.get(id); }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Equipment create(@Valid @RequestBody Equipment item) { return service.create(item); }
    @PutMapping("/{id}") public Equipment update(@PathVariable Long id, @Valid @RequestBody Equipment item) { return service.update(id, item); }
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id) { service.delete(id); }
}
