package com.KpApp.SpringBootApp.repo;

import com.SpringBootProject.OurApp.model.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SupplierRepo extends JpaRepository<Suppliers,Long> {
    Optional<Suppliers> findById(Long id);
    @Query(value = "select * from suppliers where supplier_name = ?1",nativeQuery = true)
    Suppliers findBySupplier_name(String supplier_name);

}
