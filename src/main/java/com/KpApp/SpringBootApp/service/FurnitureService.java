package com.KpApp.SpringBootApp.service;

import com.KpApp.SpringBootApp.model.*;
import com.KpApp.SpringBootApp.repo.FurnitureRepo;
import com.KpApp.SpringBootApp.validator.ImageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Service
public class FurnitureService {
    @Autowired
    private FurnitureRepo furnitureRepo;

    @Autowired
    private SupplierService supplierService;

    @Value("${upload_path}")
    private String upload_path;


    public boolean isExist(Furniture furniture){
        return furnitureRepo.findFurnitureByArticle(furniture.getArticle()) != null;
    }

    public Iterable<Furniture> findFurnitures(String id) {
        Iterable<Furniture> furnitures;
        if(id!=null && !id.isEmpty()){
            try {
                furnitures = furnitureRepo.findByArticleContaining(id);
            }catch (Exception e){
                furnitures = new ArrayList<Furniture>();
            }
        }else {
            furnitures = furnitureRepo.findAll();
        }
        return furnitures;
    }

    public boolean addFurniture(Furniture furniture, String furniture_type_enum, MultipartFile file,
                           String supplier_name) {
        if(furniture==null) return false;

        if(isExist(furniture)){
            System.out.println("Furniture AlREADY EXISTS");
            return false;
        }

        try{
            furniture.setFurniture_type(Furniture_type.valueOf(furniture_type_enum));
            if(supplier_name==null || supplier_name.isEmpty()){return false;}
            else{
                Supplier supplier = supplierService.findSupplierByName(supplier_name);
                if(supplier!=null){
                    furniture.setMain_supplier(supplier);
                }else{return false;}
            }

            if(file !=null && !file.isEmpty()){
                if(ImageValidator.validate(file,null)){
                    saveFile(furniture,file);
                }else{
                    return false;
                }
            }
            furnitureRepo.save(furniture);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean addFurniture(Furniture furniture){
        if(furniture==null) return false;
        if(isExist(furniture)){
            System.out.println("Furniture AlREADY EXISTS");
            return false;
        }
        furnitureRepo.save(furniture);
        return true;
    }

    public boolean editFurniture(Furniture furniture,String furniture_type_enum,MultipartFile file,String supplier_name){
        if(furniture==null) return false;

        if(!isExist(furniture)){
            System.out.println("Furniture NOT EXISTS");
            return false;
        }
        try {
            furniture.setFurniture_type(Furniture_type.valueOf(furniture_type_enum));
            if(supplier_name==null || supplier_name.isEmpty()){
                Supplier supplier = supplierService.findSupplierByName(supplier_name);
                if(supplier!=null){
                    furniture.setMain_supplier(supplier);
                }
            }
            if(file !=null && !file.isEmpty()){
                if(ImageValidator.validate(file,null)){
                    saveFile(furniture,file);
                }else{
                    return false;
                }
            }
            furnitureRepo.save(furniture);
            return true;
        }catch (Exception e){
            System.out.println("Invalid role");
            return false;
        }
    }

    public boolean editFurniture(Furniture furniture){
        if(furniture==null) return false;

        if(!isExist(furniture)){
            System.out.println("Furniture NOT EXISTS");
            return false;
        }
        try {
            furnitureRepo.save(furniture);
            return true;
        }catch (Exception e){
            System.out.println("Invalid role");
            return false;
        }
    }

    public boolean deleteFurniture(Furniture furniture){
        if(furniture==null) return false;

        if(!isExist(furniture)){
            System.out.println("Furniture NOT EXISTS");
            return false;
        }
        furnitureRepo.delete(furniture);
        return true;
    }

    public Furniture saveFile(Furniture furniture,MultipartFile file) throws IOException {
        if(file!=null){
            File up_dir = new File(upload_path);
            if(!up_dir.exists()){
                if(!up_dir.mkdir()){
                    throw new IOException("Unable to create directory");
                }
            }
            String uuid = UUID.randomUUID().toString();
            String result_photo_name = uuid+"."+furniture.getArticle()+"."+file.getOriginalFilename();
            furniture.setImage_name(result_photo_name);
            file.transferTo(Paths.get(upload_path+result_photo_name));
            return furniture;
        }
        return null;
    }

    public InputStream getFile(String article, String filename){
        InputStream is;
        try {
            is = new BufferedInputStream(new FileInputStream(upload_path+filename));
        } catch (IOException ex) {
            return null;
        }
        return is;
    }
}
