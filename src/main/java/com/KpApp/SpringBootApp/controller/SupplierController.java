package com.KpApp.SpringBootApp.controller;

import com.KpApp.SpringBootApp.model.Supplier;
import com.KpApp.SpringBootApp.model.Supplier;
import com.KpApp.SpringBootApp.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping("/add")
    public String addSupplier(Supplier supplier,
                          Model model){
        supplierService.addSupplier(supplier);
        return "redirect:/table/supplier";
    }

    @PostMapping("/edit")
    public String editSupplier(Supplier supplier,
                           Model model){
        supplierService.editSupplier(supplier);
        return "redirect:/table/supplier";
    }

    @PostMapping("/delete")
    public String deleteSupplier(Supplier supplier,Model model){
        supplierService.deleteSupplier(supplier);
        return "redirect:/table/supplier";
    }
}
