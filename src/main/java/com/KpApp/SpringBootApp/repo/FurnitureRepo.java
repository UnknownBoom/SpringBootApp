package com.KpApp.SpringBootApp.repo;



public interface FurnitureRepo extends JpaRepository<Furniture, String> {
    Iterable<Furniture> findAllByArticleLike(String article);
    Iterable<Furniture> findByArticleContaining(String article);
}
