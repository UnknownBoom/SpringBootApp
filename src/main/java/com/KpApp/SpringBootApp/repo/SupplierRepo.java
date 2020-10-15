package com.KpApp.SpringBootApp.repo;

import com.KpApp.SpringBootApp.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface SupplierRepo extends JpaRepository<Supplier,Long> {
    Optional<Supplier> findById(Long id);
    @Query(value = "select * from suppliers where supplier_name = ?1",nativeQuery = true)
    Supplier findBySupplier_name(String supplier_name);

}
