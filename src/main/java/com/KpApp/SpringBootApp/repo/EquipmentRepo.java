package com.KpApp.SpringBootApp.repo;

import com.KpApp.SpringBootApp.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepo extends JpaRepository<Equipment,String> {
    Equipment findEquipmentByMark(String id);
    Iterable<Equipment> findEquipmentByMarkContaining(String id);
}
