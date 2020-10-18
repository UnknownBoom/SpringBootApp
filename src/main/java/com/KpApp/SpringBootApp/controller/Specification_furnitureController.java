package com.KpApp.SpringBootApp.controller;

import com.KpApp.SpringBootApp.model.Specification_furniture;
import com.KpApp.SpringBootApp.service.Specification_furnitureService;
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
@RequestMapping("/table/specification_furniture")
public class Specification_furnitureController {

    @Autowired
    private Specification_furnitureService specification_furnitureService ;


    @GetMapping
    public String getSpecification_furniture(String id, Model model){
        Iterable<Specification_furniture> Specification_furnitures = specification_furnitureService.findSpecification_furniture(id);
        model.addAttribute("specification_furnitures",Specification_furnitures);
        return "specification_furniture";

    }
    @PostMapping("/add")
    public String addSpecification_furniture(@RequestParam(name = "product_type") String product_type,
                                            @RequestParam(name = "furniture_type") String furniture_type,
                                            @RequestParam(name = "amount") Integer amount,
                                            Model model){
        specification_furnitureService.addSpecification_furniture(product_type,furniture_type,amount);
        return "redirect:/table/specification_furniture";
    }

    @PostMapping("/edit")
    public String editSpecification_furniture(@RequestParam(name = "product_type") String product_type,
                                             @RequestParam(name = "id") Long id,
                                             @RequestParam(name = "furniture_type") String furniture_type,
                                             @RequestParam(name = "amount") Integer amount,
                                             Model model){
        specification_furnitureService.editSpecification_furniture(id,product_type,furniture_type,amount);
        return "redirect:/table/specification_furniture";
    }

    @PostMapping("/delete")
    public String deleteSpecification_furniture( @RequestParam(name = "id") Long id,Model model){
        specification_furnitureService.deleteSpecification_furniture(id);
        return "redirect:/table/specification_furniture";
    }


}
