package com.KpApp.SpringBootApp.service;

import com.KpApp.SpringBootApp.model.Furniture_type;
import com.KpApp.SpringBootApp.model.Product_type;
import com.KpApp.SpringBootApp.model.Specification_furniture;
import com.KpApp.SpringBootApp.repo.Specification_furnitureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
@Service
public class Specification_furnitureService {
    @Autowired
    private Specification_furnitureRepo specification_furnitureRepo;

    public Iterable<Specification_furniture> findSpecification_furniture(String id) {
        Iterable<Specification_furniture> Specification_furniture;
        if(id!=null && !id.isEmpty()){
            try {
                Specification_furniture = Collections.singleton(specification_furnitureRepo.findById(Long.parseLong(id)).get());
            }catch (Exception e){
                Specification_furniture = new ArrayList<Specification_furniture>();
            }
        }else {
            Specification_furniture = specification_furnitureRepo.findAll();
        }
        return Specification_furniture;
    }

    public Specification_furniture findSpecification_furnitureById(Long id) {
        Specification_furniture Specification_furniture = specification_furnitureRepo.findById(id).get();
        return Specification_furniture;
    }


    public boolean addSpecification_furniture(String  product_type,String furniture_type,Integer amount){
        if(product_type!=null && !product_type.isEmpty()
                && furniture_type!=null && !furniture_type.isEmpty()
                && amount!=null){
            try {
                Specification_furniture Specification_furniture = new Specification_furniture();
                Specification_furniture.setProduct_type(Product_type.valueOf(product_type));
                Specification_furniture.setFurniture_type(Furniture_type.valueOf(furniture_type));
                Specification_furniture.setAmount(amount);
                specification_furnitureRepo.save(Specification_furniture);
                return true;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean editSpecification_furniture(Long id,String  product_type,String furniture_type,Integer amount){
        if(id==null) return false;
        if(product_type!=null && !product_type.isEmpty()
                && furniture_type!=null && !furniture_type.isEmpty()
                && amount!=null){
            try {
                Specification_furniture Specification_furniture = specification_furnitureRepo.findById(id).get();
                Specification_furniture.setFurniture_type(Furniture_type.valueOf(furniture_type));
                Specification_furniture.setProduct_type(Product_type.valueOf(product_type));
                Specification_furniture.setAmount(amount);

                specification_furnitureRepo.save(Specification_furniture);
                return true;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean deleteSpecification_furniture(Long id){
        if(id==null) return false;

        try{
            Specification_furniture Specification_furniture = specification_furnitureRepo.findById(id).get();
            specification_furnitureRepo.delete(Specification_furniture);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
