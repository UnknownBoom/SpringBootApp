package com.KpApp.SpringBootApp.service;

import com.KpApp.SpringBootApp.model.Material_type;
import com.KpApp.SpringBootApp.model.Product_type;
import com.KpApp.SpringBootApp.model.Specification_material;
import com.KpApp.SpringBootApp.repo.Specification_materialRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class Specification_materialService {
    @Autowired
    private Specification_materialRepo specification_materialRepo;

    public Iterable<Specification_material> findSpecification_material(String id) {
        Iterable<Specification_material> Specification_material;
        if(id!=null && !id.isEmpty()){
            try {
                Specification_material = Collections.singleton(specification_materialRepo.findById(Long.parseLong(id)).get());
            }catch (Exception e){
                Specification_material = new ArrayList<Specification_material>();
            }
        }else {
            Specification_material = specification_materialRepo.findAll();
        }
        return Specification_material;
    }

    public Specification_material findSpecification_materialById(Long id) {
        Specification_material Specification_material = specification_materialRepo.findById(id).get();
        return Specification_material;
    }


    public boolean addSpecification_material(String  product_type,String material_type,Integer amount){
        if(product_type!=null && !product_type.isEmpty()
                && material_type!=null && !material_type.isEmpty()
                && amount!=null){
            try {
                Specification_material Specification_material = new Specification_material();
                Specification_material.setProduct_type(Product_type.valueOf(product_type));
                Specification_material.setMaterial(Material_type.valueOf(material_type));
                Specification_material.setAmount(amount);
                specification_materialRepo.save(Specification_material);
                return true;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean editSpecification_material(Long id,String  product_type,String material_type,Integer amount){
        if(id==null) return false;
        if(product_type!=null && !product_type.isEmpty()
                && material_type!=null && !material_type.isEmpty()
                && amount!=null){
            try {
                Specification_material Specification_material = specification_materialRepo.findById(id).get();
                Specification_material.setMaterial(Material_type.valueOf(material_type));
                Specification_material.setProduct_type(Product_type.valueOf(product_type));
                Specification_material.setAmount(amount);

                specification_materialRepo.save(Specification_material);
                return true;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean deleteSpecification_material(Long id){
        if(id==null) return false;

        try{
            Specification_material Specification_material = specification_materialRepo.findById(id).get();
            specification_materialRepo.delete(Specification_material);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
