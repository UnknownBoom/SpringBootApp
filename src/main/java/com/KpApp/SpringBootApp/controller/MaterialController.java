package com.KpApp.SpringBootApp.controller;

import com.KpApp.SpringBootApp.model.Material;
import com.KpApp.SpringBootApp.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/table/material")
public class MaterialController {
    @Autowired
    private MaterialService materialService;

    @GetMapping
    public String getMaterial(String id, Model model){
        Iterable<Material> materials = materialService.findMaterial(id);
        model.addAttribute("materials",materials);
        return "Material";

    }
    @PostMapping("/add")
    public String addMaterial(Material material, @RequestParam(name = "material_type_enum") String Material_type_enum,
                              @RequestParam(required = false,name="image_name_") MultipartFile file,
                               @RequestParam(required = true,name="supplier_name") String supplier_name,
                               Model model){
        materialService.addMaterial(material,Material_type_enum,supplier_name,file);
        return "redirect:/table/Material";
    }

    @PostMapping("/edit")
    public String editMaterial(Material material,@RequestParam(name = "material_type_enum",required = false) String Material_type_enum,
                               @RequestParam(required = false,name="image_name_") MultipartFile file,
                                @RequestParam(required = true,name="supplier_name") String supplier_name,
                                Model model){
        materialService.editMaterial(material,Material_type_enum,supplier_name,file);
        return "redirect:/table/Material";
    }

    @PostMapping("/delete")
    public String deleteMaterial(Material material,Model model){
        materialService.deleteMaterial(material);
        return "redirect:/table/Material";
    }

    
}
