package com.KpApp.SpringBootApp.service;

import com.KpApp.SpringBootApp.model.Equipment_type;
import com.KpApp.SpringBootApp.model.Operation_type;
import com.KpApp.SpringBootApp.model.Product_type;
import com.KpApp.SpringBootApp.model.Specification_operation;
import com.KpApp.SpringBootApp.repo.Specification_operationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class Specification_operationService {
    @Autowired
    private Specification_operationRepo specification_operationRepo;

    public Iterable<Specification_operation> findSpecification_operation(String id) {
        Iterable<Specification_operation> specification_operation;
        if(id!=null && !id.isEmpty()){
            try {
                specification_operation = Collections.singleton(specification_operationRepo.findById(Long.parseLong(id)).get());
            }catch (Exception e){
                specification_operation = new ArrayList<Specification_operation>();
            }
        }else {
            specification_operation = specification_operationRepo.findAll();
        }
        return specification_operation;
    }

    public Specification_operation findSpecification_operationById(Long id) {
        Specification_operation Specification_operation = specification_operationRepo.findById(id).get();
        return Specification_operation;
    }


    public boolean addSpecification_operation(String  product_type,String equipment_type,String op_type,Integer time){
        if(product_type!=null && !product_type.isEmpty()
                && equipment_type!=null && !equipment_type.isEmpty()
                && op_type!=null && !op_type.isEmpty()
                && time!=null){
            try {
                Specification_operation Specification_operation = new Specification_operation();
                Specification_operation.setProduct_type(Product_type.valueOf(product_type));
                Specification_operation.setEquipment_type(Equipment_type.valueOf(equipment_type));
                Specification_operation.setOperation(Operation_type.valueOf(op_type));
                Specification_operation.setOperation_time(time);
                specification_operationRepo.save(Specification_operation);
                return true;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean editSpecification_operation(Long id,String  product_type,String equipment_type,String op_type,Integer time){
        if(id==null) return false;
        if(product_type!=null && !product_type.isEmpty()
                && equipment_type!=null && !equipment_type.isEmpty()
                && op_type!=null && !op_type.isEmpty()
                && time!=null){
            try {
                Specification_operation Specification_operation = specification_operationRepo.findById(id).get();
                Specification_operation.setEquipment_type(Equipment_type.valueOf(equipment_type));
                Specification_operation.setProduct_type(Product_type.valueOf(product_type));
                Specification_operation.setOperation(Operation_type.valueOf(op_type));
                Specification_operation.setOperation_time(time);
                specification_operationRepo.save(Specification_operation);
                return true;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean deleteSpecification_operation(Long id){
        if(id==null) return false;

        try{
            Specification_operation Specification_operation = specification_operationRepo.findById(id).get();
            specification_operationRepo.delete(Specification_operation);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
