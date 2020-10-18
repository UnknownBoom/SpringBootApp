package com.KpApp.SpringBootApp.service;

import com.KpApp.SpringBootApp.model.Product_type;
import com.KpApp.SpringBootApp.model.Specification_unit;
import com.KpApp.SpringBootApp.repo.Specification_unitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Specification_unitService {
    
    @Autowired
    private Specification_unitRepo specification_unitRepo;

        public Iterable<Specification_unit> findSpecification_unit(String id) {
            Iterable<Specification_unit> Specification_units;
            if(id!=null && !id.isEmpty()){
                try {
                    ArrayList list = new ArrayList();
                    Specification_unit byId = specification_unitRepo.findSpecification_unitById(Long.parseLong(id));
                    if(byId!=null){
                        list.add(byId);
                    }
                    Specification_units = list;
                }catch (Exception e){
                    Specification_units = new ArrayList<Specification_unit>();
                }
            }else {
                Specification_units = specification_unitRepo.findAll();
            }
            return Specification_units;
        }

        public Specification_unit findSpecification_unitById(Long id) {
            Specification_unit Specification_unit = specification_unitRepo.findSpecification_unitById(id);
            return Specification_unit;
        }


        public boolean addSpecification_unit(String  product_type,String product_unit,Integer amount){
            if(product_type!=null && !product_type.isEmpty()
            && product_unit!=null && !product_unit.isEmpty()
            && amount!=null){
                try {
                    Specification_unit specification_unit = new Specification_unit();
                    specification_unit.setProduct_type(Product_type.valueOf(product_type));
                    specification_unit.setProduct_unit(Product_type.valueOf(product_unit));
                    specification_unit.setAmount(amount);
                    specification_unitRepo.save(specification_unit);
                    return true;
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    return false;
                }
            }
            return false;
        }

        public boolean editSpecification_unit(Long id,String  product_type,String product_unit,Integer amount){
            if(id==null) return false;
            if(product_type!=null && !product_type.isEmpty()
                    && product_unit!=null && !product_unit.isEmpty()
                    && amount!=null){
                try {
                    Specification_unit specification_unit = specification_unitRepo.findSpecification_unitById(id);
                    if(specification_unit==null) return false;
                    specification_unit.setProduct_type(Product_type.valueOf(product_type));
                    specification_unit.setProduct_unit(Product_type.valueOf(product_unit));
                    specification_unit.setAmount(amount);
                    specification_unitRepo.save(specification_unit);
                    return true;
                }catch (Exception e){
                    System.out.println(e.getMessage());
                    return false;
                }
            }
            return false;
        }

        public boolean deleteSpecification_unit(Long id){
            if(id==null) return false;

            try{
                Specification_unit specification_unit = specification_unitRepo.findSpecification_unitById(id);
                specification_unitRepo.delete(specification_unit);
            }catch (Exception e){
                return false;
            }
            return true;
        }
}

