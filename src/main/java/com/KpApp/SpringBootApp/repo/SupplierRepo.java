package com.KpApp.SpringBootApp.repo;

import com.KpApp.SpringBootApp.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface SupplierRepo extends JpaRepository<Supplier,Long> {
    Supplier findSupplierById(Long id);
    @Query(value = "select * from suppliers where supplier_name = ?1",nativeQuery = true)
    Supplier findBySupplier_name(String supplier_name);

}
