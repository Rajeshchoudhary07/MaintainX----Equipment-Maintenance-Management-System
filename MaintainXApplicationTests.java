package com.maintainx;

import com.maintainx.model.Equipment;
import com.maintainx.model.EquipmentStatus;
import com.maintainx.repository.EquipmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MaintainXApplicationTests {
    @Autowired EquipmentRepository equipmentRepository;

    @Test
    void applicationContextStarts() {
        assertThat(equipmentRepository).isNotNull();
    }

    @Test
    void equipmentCanBePersistedAndRetrieved() {
        Equipment item = new Equipment();
        item.setName("Test Pump");
        item.setCategory("Pump");
        item.setLocation("Test Plant");
        item.setSerialNumber("TEST-" + System.nanoTime());
        item.setStatus(EquipmentStatus.ACTIVE);
        Equipment saved = equipmentRepository.save(item);
        assertThat(equipmentRepository.findById(saved.getId())).isPresent();
        equipmentRepository.deleteById(saved.getId());
    }
}
