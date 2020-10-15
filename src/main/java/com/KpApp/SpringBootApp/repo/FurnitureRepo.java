package com.KpApp.SpringBootApp.repo;


import com.KpApp.SpringBootApp.model.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FurnitureRepo extends JpaRepository<Furniture, String> {
    Iterable<Furniture> findAllByArticleLike(String article);
    Iterable<Furniture> findByArticleContaining(String article);
}
