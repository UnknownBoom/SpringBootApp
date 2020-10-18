package com.KpApp.SpringBootApp.controller;

import com.KpApp.SpringBootApp.model.Specification_material;
import com.KpApp.SpringBootApp.service.Specification_materialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAnyAuthority('Manager','Master','Director','Deputy_director')")
@RequestMapping("/table/specification_material")
public class Specification_materialController {
    @Autowired
    private Specification_materialService specification_materialService ;


    @GetMapping
    public String getSpecification_material(String id, Model model){
        Iterable<Specification_material> Specification_materials = specification_materialService.findSpecification_material(id);
        model.addAttribute("specification_materials",Specification_materials);
        return "specification_material";

    }
    @PostMapping("/add")
    public String addSpecification_material(@RequestParam(name = "product_type") String product_type,
                                             @RequestParam(name = "material_type") String material_type,
                                             @RequestParam(name = "amount") Integer amount,
                                             Model model){
        specification_materialService.addSpecification_material(product_type,material_type,amount);
        return "redirect:/table/specification_material";
    }

    @PostMapping("/edit")
    public String editSpecification_material(@RequestParam(name = "product_type") String product_type,
                                              @RequestParam(name = "id") Long id,
                                             @RequestParam(name = "material_type") String material_type,
                                             @RequestParam(name = "amount") Integer amount,
                                              Model model){
        specification_materialService.editSpecification_material(id,product_type,material_type,amount);
        return "redirect:/table/specification_material";
    }

    @PostMapping("/delete")
    public String deleteSpecification_material( @RequestParam(name = "id") Long id,Model model){
        specification_materialService.deleteSpecification_material(id);
        return "redirect:/table/specification_material";
    }
}
