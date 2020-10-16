package com.KpApp.SpringBootApp.controller;

import com.KpApp.SpringBootApp.model.Product_type_dimension;
import com.KpApp.SpringBootApp.service.Product_type_dimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("table/product_type_dimension")
public class product_type_dimensionController {

    @Autowired
    private Product_type_dimensionService product_type_dimensionService;


    @GetMapping
    public String getProduct_type_dimension(String id, Model model){
        Iterable<Product_type_dimension> Product_type_dimensions = product_type_dimensionService.findProduct_type_dimension(id);
        model.addAttribute("product_type_dimensions",Product_type_dimensions);
        return "product_type_dimension";

    }
    @PostMapping("/add")
    public String addProduct_type_dimension(Product_type_dimension Product_type_dimension,
                              @RequestParam(name = "product_type_enum") String product_type_enum,
                              Model model){
        product_type_dimensionService.addProduct_type_dimension(Product_type_dimension,product_type_enum);
        return "redirect:/table/product_type_dimension";
    }

    @PostMapping("/edit")
    public String editProduct_type_dimension(Product_type_dimension Product_type_dimension,
                                @RequestParam(name = "product_type_enum") String product_type_enum,
                               Model model){
        product_type_dimensionService.editProduct_type_dimension(Product_type_dimension,product_type_enum);
        return "redirect:/table/product_type_dimension";
    }

    @PostMapping("/delete")
    public String deleteProduct_type_dimension(Product_type_dimension Product_type_dimension,Model model){
        product_type_dimensionService.deleteProduct_type_dimension(Product_type_dimension);
        return "redirect:/table/product_type_dimension";
    }

}
