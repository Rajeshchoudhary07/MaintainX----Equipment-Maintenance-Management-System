package com.maintainx.repository;
import com.maintainx.model.MaintenanceSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface MaintenanceScheduleRepository extends JpaRepository<MaintenanceSchedule, Long> {
    List<MaintenanceSchedule> findByEquipmentId(Long equipmentId);
}
