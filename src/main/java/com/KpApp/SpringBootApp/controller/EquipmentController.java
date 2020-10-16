package com.KpApp.SpringBootApp.controller;

import com.KpApp.SpringBootApp.model.Equipment;
import com.KpApp.SpringBootApp.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("table/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    public String getEquipment(String mark, Model model){
        Iterable<Equipment> equipments = equipmentService.getEquipments(mark);
        model.addAttribute("equipments",equipments);
        return "equipment";

    }
    @PostMapping("/add")
    public String addEquipment(Equipment equipment,@RequestParam(name = "equipment_type_enum") String equipment_type_enum,
                               Model model){
        equipmentService.addEquipment(equipment,equipment_type_enum);
        return "redirect:/table/equipment";
    }

    @PostMapping("/edit")
    public String editEquipment(Equipment equipment,@RequestParam(name = "equipment_type_enum",required = false) String equipment_type_enum,
                               Model model){
        equipmentService.editEquipment(equipment,equipment_type_enum);
        return "redirect:/table/equipment";
    }

    @PostMapping("/delete")
    public String deleteEquipment(Equipment equipment,Model model){
        equipmentService.deleteEquipment(equipment);
        return "redirect:/table/equipment";
    }
}
