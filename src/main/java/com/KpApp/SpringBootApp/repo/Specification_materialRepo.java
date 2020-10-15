package com.KpApp.SpringBootApp.repo;

import com.KpApp.SpringBootApp.model.Specification_material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Specification_materialRepo extends JpaRepository<Specification_material, Long> {

    Specification_material findSpecification_materialById(Long id);
}
