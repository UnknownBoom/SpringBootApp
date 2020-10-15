package com.KpApp.SpringBootApp.repo;


import com.KpApp.SpringBootApp.model.Specification_operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface Specification_operationRepo extends JpaRepository<Specification_operation,Long> {
    Optional<Specification_operation> findById(Long Id);

}

