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

        try{
            Product_type_dimension.setProduct_type(Product_type.valueOf(product_type_enum));
        }catch (Exception e){
            return false;
        }
        product_type_dimensionRepo.save(Product_type_dimension);
        return true;
    }

    public boolean deleteProduct_type_dimension(String  Product_type_enum){
        if(Product_type_enum==null) return false;

        try{
            Product_type product_type = Product_type.valueOf(Product_type_enum);
            Product_type_dimension product_type_dimension = new Product_type_dimension();
            product_type_dimension.setProduct_type(product_type);
            product_type_dimensionRepo.delete(product_type_dimension);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
