package com.KpApp.SpringBootApp.service;

import com.KpApp.SpringBootApp.model.Product_type;
import com.KpApp.SpringBootApp.model.Product_type_dimension;
import com.KpApp.SpringBootApp.model.Product_type_dimension;
import com.KpApp.SpringBootApp.repo.Product_type_dimensionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Product_type_dimensionService {
    @Autowired
    private Product_type_dimensionRepo product_type_dimensionRepo;

    public boolean isExist(Product_type_dimension Product_type_dimension){
        return product_type_dimensionRepo.findProductTypeDimensionByProductType(Product_type_dimension.getProduct_type().name()) != null;
    }

    public Iterable<Product_type_dimension> findProduct_type_dimension(String id) {
        Iterable<Product_type_dimension> Product_type_dimensions;
        if(id!=null && !id.isEmpty()){
            try {
                Product_type_dimensions = product_type_dimensionRepo.findProductTypeDimensionByProductTypeContaing(id);
            }catch (Exception e){
                Product_type_dimensions = new ArrayList<Product_type_dimension>();
            }
        }else {
            Product_type_dimensions = product_type_dimensionRepo.findAll();
        }
        return Product_type_dimensions;
    }

    public Product_type_dimension findProduct_type_dimensionByName(String name) {
        Product_type_dimension Product_type_dimension = product_type_dimensionRepo.findProductTypeDimensionByProductType(name);
        return Product_type_dimension;
    }


    public boolean addProduct_type_dimension(Product_type_dimension Product_type_dimension,String type){
        if(Product_type_dimension==null) return false;
        if(isExist(Product_type_dimension)){
            System.out.println("Product_type_dimension AlREADY EXISTS");
            return false;
        }
        try{
            Product_type_dimension.setProduct_type(Product_type.valueOf(type));
        }catch (Exception e){
            return false;
        }
        product_type_dimensionRepo.save(Product_type_dimension);
        return true;
    }

    public boolean editProduct_type_dimension(Product_type_dimension Product_type_dimension,String product_type_enum){
        if(Product_type_dimension==null) return false;

        if(!isExist(Product_type_dimension)){
            System.out.println("Product_type_dimension NOT EXISTS");
            return false;
        }
        try{
            Product_type_dimension.setProduct_type(Product_type.valueOf(product_type_enum));
        }catch (Exception e){
            return false;
        }
        product_type_dimensionRepo.save(Product_type_dimension);
        return true;
    }

    public boolean deleteProduct_type_dimension(Product_type_dimension Product_type_dimension){
        if(Product_type_dimension==null) return false;

        if(!isExist(Product_type_dimension)){
            System.out.println("Product_type_dimension NOT EXISTS");
            return false;
        }
        product_type_dimensionRepo.delete(Product_type_dimension);
        return true;
    }
}
