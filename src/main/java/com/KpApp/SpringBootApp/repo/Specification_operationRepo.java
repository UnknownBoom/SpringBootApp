package com.KpApp.SpringBootApp.repo;


import com.SpringBootProject.OurApp.model.OperationSpecification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface Specification_operationRepo extends JpaRepository<Specification_operation,Long> {
    Optional<OperationSpecification> findById(Long Id);

}

