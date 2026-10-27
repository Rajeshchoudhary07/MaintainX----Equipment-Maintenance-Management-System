package com.maintainx.repository;
import com.maintainx.model.Equipment;
import com.maintainx.model.EquipmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    boolean existsBySerialNumber(String serialNumber);
    List<Equipment> findByNameContainingIgnoreCaseOrCategoryContainingIgnoreCase(String name, String category);
    List<Equipment> findByStatus(EquipmentStatus status);
}
