package com.KpApp.SpringBootApp.controller;

import com.KpApp.SpringBootApp.model.Specification_unit;
import com.KpApp.SpringBootApp.service.Specification_unitService;
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
@RequestMapping("/table/specification_unit")
public class Specification_unitController {

    @Autowired
    private Specification_unitService specification_unitService;


    @GetMapping
    public String getSpecification_unit(String id, Model model){
        Iterable<Specification_unit> specification_units = specification_unitService.findSpecification_unit(id);
        model.addAttribute("specification_units",specification_units);
        return "specification_unit";

    }
    @PostMapping("/add")
    public String addSpecification_unit(@RequestParam(name = "product_type") String product_type,
            @RequestParam(name = "product_unit") String product_unit,
            @RequestParam(name = "amount") Integer amount,
                                            Model model){
        specification_unitService.addSpecification_unit(product_type,product_unit,amount);
        return "redirect:/table/specification_unit";
    }

    @PostMapping("/edit")
    public String editSpecification_unit(@RequestParam(name = "product_type") String product_type,
                                         @RequestParam(name = "product_unit") String product_unit,
                                         @RequestParam(name = "id") Long id,
                                         @RequestParam(name = "amount") Integer amount,
                                             Model model){
        specification_unitService.editSpecification_unit(id,product_type,product_unit,amount);
        return "redirect:/table/specification_unit";
    }

    @PostMapping("/delete")
    public String deleteSpecification_unit( @RequestParam(name = "id") Long id,Model model){
        specification_unitService.deleteSpecification_unit(id);
        return "redirect:/table/specification_unit";
    }
}
