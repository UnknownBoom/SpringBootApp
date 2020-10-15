package com.KpApp.SpringBootApp.service;

import com.KpApp.SpringBootApp.model.Supplier;
import com.KpApp.SpringBootApp.repo.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SupplierService {
    
    @Autowired
    private SupplierRepo supplierRepo;

    public boolean isExist(Supplier Supplier){
        return supplierRepo.findSupplierById(Supplier.getId()) != null;
    }

    public Iterable<Supplier> findSupplier(String id) {
        Iterable<Supplier> Suppliers;
        if(id!=null && !id.isEmpty()){
            try {
                long idL = Long.parseLong(id);
                ArrayList<Supplier> SupplierList = new ArrayList<>();
                SupplierList.add(supplierRepo.findSupplierById(idL));
                Suppliers = SupplierList;
            }catch (Exception e){
                Suppliers = new ArrayList<Supplier>();
            }
        }else {
            Suppliers = supplierRepo.findAll();
        }
        return Suppliers;
    }
    

    public boolean addSupplier(Supplier Supplier){
        if(Supplier==null) return false;
        if(isExist(Supplier)){
            System.out.println("Supplier AlREADY EXISTS");
            return false;
        }
        supplierRepo.save(Supplier);
        return true;
    }

    public boolean editSupplier(Supplier Supplier){
        if(Supplier==null) return false;

        if(!isExist(Supplier)){
            System.out.println("Supplier NOT EXISTS");
            return false;
        }
        supplierRepo.save(Supplier);
        return true;
    }

    public boolean deleteSupplier(Supplier Supplier){
        if(Supplier==null) return false;

        if(!isExist(Supplier)){
            System.out.println("Supplier NOT EXISTS");
            return false;
        }
        supplierRepo.delete(Supplier);
        return true;
    }
}
