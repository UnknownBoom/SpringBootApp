package com.KpApp.SpringBootApp.controller;


import com.KpApp.SpringBootApp.model.Furniture;
import com.KpApp.SpringBootApp.service.FurnitureService;
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
@RequestMapping("/table/furniture")
public class FurnitureController {

    @Autowired
    private FurnitureService furnitureService;

    @GetMapping
    public String getFurniture(String id, Model model){
        Iterable<Furniture> furnitures = furnitureService.findFurnitures(id);
        model.addAttribute("furnitures",furnitures);
        return "furniture";

    }
    @PostMapping("/add")
    public String addFurniture(Furniture furniture, @RequestParam(name = "furniture_type_enum") String furniture_type_enum,
                           @RequestParam(required = false,name="image_name_") MultipartFile file,
                           @RequestParam(required = true,name="supplier_name") String supplier_name,
                           Model model){
        furnitureService.addFurniture(furniture,furniture_type_enum,file,supplier_name);
        return "redirect:/table/furniture";
    }

    @PostMapping("/edit")
    public String editFurniture(Furniture Furniture,@RequestParam(name = "furniture_type_enum",required = false) String furniture_type_enum,
                            @RequestParam(name="Furniture_schema_image",required = false) MultipartFile file,
                            @RequestParam(required = true,name="supplier_name") String supplier_name,
                            Model model){
        furnitureService.editFurniture(Furniture,furniture_type_enum,file,supplier_name);
        return "redirect:/table/furniture";
    }

    @PostMapping("/delete")
    public String deleteFurniture(Furniture furniture,Model model){
        furnitureService.deleteFurniture(furniture);
        return "redirect:/table/furniture";
    }

    @GetMapping("/{id}/{file_name}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable("file_name") String fileName,
                                                       HttpServletResponse response,
                                                       @PathVariable String id) throws IOException {
        InputStream photo_is = furnitureService.getFile(id, fileName);
        if(photo_is==null){
            throw new IOException("IOError open file to input stream");
        }
        try{
            return ResponseEntity.ok()
                    .contentLength(photo_is.available())
                    .contentType(MediaType.IMAGE_JPEG)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(new InputStreamResource(photo_is));
        }catch (IOException e){
            throw new IOException("IOError writing file to output stream");
        }
    }
}
