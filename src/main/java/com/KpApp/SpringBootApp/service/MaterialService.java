package com.KpApp.SpringBootApp.service;

import com.KpApp.SpringBootApp.model.*;
import com.KpApp.SpringBootApp.repo.MaterialRepo;
import com.KpApp.SpringBootApp.validator.ImageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class MaterialService {
    
    @Autowired
    private MaterialRepo materialRepo;

    @Autowired
    private SupplierService supplierService;

    @Value("${upload_path}")
    private String upload_path;


    public boolean isExist(Material material){
        return materialRepo.findMaterialByArticle(material.getArticle()) != null;
    }

    public Iterable<Material> findMaterial(String id) {
        Iterable<Material> materials;
        if(id!=null && !id.isEmpty()){
            try {
                materials = materialRepo.findByArticleContaining(id);
            }catch (Exception e){
                materials = new ArrayList<Material>();
            }
        }else {
            materials = materialRepo.findAll();
        }
        return materials;
    }

    public boolean addMaterial(Material material, String material_type_enum,
                                String supplier_name,MultipartFile file) {
        if(material==null) return false;

        if(isExist(material)){
            System.out.println("Furniture AlREADY EXISTS");
            return false;
        }

        try{
            material.setMaterial_type(Material_type.valueOf(material_type_enum));
            if(supplier_name!=null && !supplier_name.isEmpty()){
                Supplier supplier = supplierService.findSupplierByName(supplier_name);
                if(supplier!=null){
                    material.setMain_supplier(supplier);
                }else{
                    return false;
                }
                if(file !=null && !file.isEmpty()){
                    if(ImageValidator.validate(file,null)){
                        saveFile(material,file);
                    }else{
                        return false;
                    }
                }
            }

            materialRepo.save(material);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean addMaterial(Material material){
        if(material==null) return false;
        if(isExist(material)){
            System.out.println("Furniture AlREADY EXISTS");
            return false;
        }
        materialRepo.save(material);
        return true;
    }

    public boolean editMaterial(Material material,String material_type_enum,String supplier_name,MultipartFile file){
        if(material==null) return false;

        if(!isExist(material)){
            System.out.println("Furniture NOT EXISTS");
            return false;
        }
        try {
            material.setMaterial_type(Material_type.valueOf(material_type_enum));
            if(supplier_name!=null && !supplier_name.isEmpty()){
                Supplier supplier = supplierService.findSupplierByName(supplier_name);
                if(supplier!=null){
                    material.setMain_supplier(supplier);
                }else{
                    return false;
                }
            }
            if(file !=null && !file.isEmpty()){
                if(ImageValidator.validate(file,null)){
                    saveFile(material,file);
                }else{
                    return false;
                }
            }else{
                material.setImage_name(materialRepo.findMaterialByArticle(material.getArticle()).getImage_name());
            }
            materialRepo.save(material);
            return true;
        }catch (Exception e){
            System.out.println("Invalid role");
            return false;
        }
    }


    public boolean editMaterial(Material material){
        if(material==null) return false;

        if(!isExist(material)){
            System.out.println("Furniture NOT EXISTS");
            return false;
        }
        try {
            materialRepo.save(material);
            return true;
        }catch (Exception e){
            System.out.println("Invalid role");
            return false;
        }
    }

    public boolean deleteMaterial(Material material){
        if(material==null) return false;

        if(!isExist(material)){
            System.out.println("Furniture NOT EXISTS");
            return false;
        }
        materialRepo.delete(material);
        return true;
    }

    public Material saveFile(Material material,MultipartFile file) throws IOException {
        if(file!=null){
            File up_dir = new File(upload_path);
            if(!up_dir.exists()){
                if(!up_dir.mkdir()){
                    throw new IOException("Unable to create directory");
                }
            }
            String uuid = UUID.randomUUID().toString();
            String result_photo_name = uuid+"."+material.getArticle()+"."+file.getOriginalFilename();
            material.setImage_name(result_photo_name);
            file.transferTo(Paths.get(upload_path+result_photo_name));
            return material;
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
