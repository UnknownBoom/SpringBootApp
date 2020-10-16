package com.KpApp.SpringBootApp.service;

import com.KpApp.SpringBootApp.model.Equipment;
import com.KpApp.SpringBootApp.model.Equipment_type;
import com.KpApp.SpringBootApp.repo.EquipmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepo equipmentRepo;

    public boolean isExist(Equipment equipment){
        if(equipment==null) return false;
        Equipment byMark = equipmentRepo.findEquipmentByMark(equipment.getMark());
        if(byMark!=null){
            return true;
        }
        return false;
    }

    public Iterable<Equipment> getEquipments(String mark) {
        Iterable<Equipment> equipments;
        if(mark!=null && !mark.isEmpty()){
            equipments = equipmentRepo.findEquipmentByMarkContaining(mark);
        }else{
            equipments = equipmentRepo.findAll();
        }
        return equipments;
    }

    public boolean addEquipment(Equipment equipment, String equipment_type){
        if(equipment==null) return false;

        if(isExist(equipment)){
            System.out.println("Equipment AlREADY EXISTS");
            return false;
        }
        try {
            equipment.setEquipment_type(Equipment_type.valueOf(equipment_type));
            equipmentRepo.save(equipment);
            return true;
        }catch (Exception e){
            System.out.println("Invalid equipment_type");
            return false;
        }
    }

    public boolean addEquipment(Equipment equipment){
        if(equipment==null) return false;

        if(!isExist(equipment)){
            System.out.println("Equipment AlREADY EXISTS");
            return false;
        }

        equipmentRepo.save(equipment);
        return true;
    }

    public boolean editEquipment(Equipment equipment,String equipment_type){
        if(equipment==null) return false;

        if(!isExist(equipment)){
            System.out.println("Equipment NOT EXISTS");
            return false;
        }
        try {
            if(equipment_type!=null){
                equipment.setEquipment_type(Equipment_type.valueOf(equipment_type));
            }
            equipmentRepo.save(equipment);
            return true;
        }catch (Exception e){
            System.out.println("Invalid equipment_type");
            return false;
        }
    }
    public boolean editEquipment(Equipment equipment){
        if(equipment==null) return false;

        if(!isExist(equipment)){
            System.out.println("Equipment NOT EXISTS");
            return false;
        }
        try {
            equipmentRepo.save(equipment);
            return true;
        }catch (Exception e){
            System.out.println("Invalid equipment_type");
            return false;
        }
    }

    public boolean deleteEquipment(Equipment equipment){
        if(equipment==null) return false;

        if(!isExist(equipment)){
            System.out.println("Equipment NOT EXISTS");
            return false;
        }
        equipmentRepo.delete(equipment);
        return true;
    }
}
