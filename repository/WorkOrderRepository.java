package com.maintainx.repository;
import com.maintainx.model.WorkOrder;
import com.maintainx.model.WorkOrderStatus;
import com.maintainx.model.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
    List<WorkOrder> findByStatus(WorkOrderStatus status);
    List<WorkOrder> findByPriority(Priority priority);
    List<WorkOrder> findByEquipmentId(Long equipmentId);
}
