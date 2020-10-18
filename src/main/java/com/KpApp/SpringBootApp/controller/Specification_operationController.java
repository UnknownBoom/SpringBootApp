package com.KpApp.SpringBootApp.controller;

import com.KpApp.SpringBootApp.model.Specification_operation;
import com.KpApp.SpringBootApp.service.Specification_operationService;
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
@RequestMapping("/table/specification_operation")
public class Specification_operationController {

        @Autowired
        private Specification_operationService specification_operationService ;


        @GetMapping
        public String getSpecification_operation(String id, Model model){
            Iterable<Specification_operation> Specification_operations = specification_operationService.findSpecification_operation(id);
            model.addAttribute("specification_operations",Specification_operations);
            return "specification_operation";

        }
        @PostMapping("/add")
        public String addSpecification_operation(@RequestParam(name = "product_type") String product_type,
                                            @RequestParam(name = "equipment_type") String equipment_type,
                                            @RequestParam(name = "op_type") String op_type,
                                            @RequestParam(name = "time") Integer time,
                                            Model model){
            specification_operationService.addSpecification_operation(product_type,equipment_type,op_type,time);
            return "redirect:/table/specification_operation";
        }

        @PostMapping("/edit")
        public String editSpecification_operation(@RequestParam(name = "product_type") String product_type,
                                             @RequestParam(name = "equipment_type") String product_unit,
                                             @RequestParam(name = "id") Long id,
                                              @RequestParam(name = "op_type") String op_type,
                                             @RequestParam(name = "time") Integer time,
                                             Model model){
            specification_operationService.editSpecification_operation(id,product_type,product_unit,op_type,time);
            return "redirect:/table/specification_operation";
        }

        @PostMapping("/delete")
        public String deleteSpecification_operation( @RequestParam(name = "id") Long id,Model model){
            specification_operationService.deleteSpecification_operation(id);
            return "redirect:/table/specification_operation";
        }
}
