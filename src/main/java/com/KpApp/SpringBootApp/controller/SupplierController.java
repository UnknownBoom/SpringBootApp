package com.KpApp.SpringBootApp.controller;

import com.KpApp.SpringBootApp.model.Supplier;
import com.KpApp.SpringBootApp.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/table/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;


    @GetMapping
    public String getSupplier(String id, Model model){
        Iterable<Supplier> suppliers = supplierService.findSupplier(id);
        model.addAttribute("suppliers",suppliers);
        return "Supplier";

    }
    @PreAuthorize("hasAnyAuthority('Manager','Master','Director','Deputy_director')")
    @PostMapping("/add")
    public String addSupplier(Supplier supplier,
                          Model model){
        supplierService.addSupplier(supplier);
        return "redirect:/table/supplier";
    }
    @PreAuthorize("hasAnyAuthority('Manager','Master','Director','Deputy_director')")
    @PostMapping("/edit")
    public String editSupplier(Supplier supplier,
                           Model model){
        supplierService.editSupplier(supplier);
        return "redirect:/table/supplier";
    }
    @PreAuthorize("hasAnyAuthority('Manager','Master','Director','Deputy_director')")
    @PostMapping("/delete")
    public String deleteSupplier(Supplier supplier,Model model){
        supplierService.deleteSupplier(supplier);
        return "redirect:/table/supplier";
    }
}
