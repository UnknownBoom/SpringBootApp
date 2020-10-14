package com.KpApp.SpringBootApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepo extends JpaRepository<Material,String> {

    List<Material> findByArticleContaining(String article);
}
