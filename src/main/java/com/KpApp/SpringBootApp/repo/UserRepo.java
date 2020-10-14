package com.KpApp.SpringBootApp.repo;



public interface UserRepo extends JpaRepository<Users,Long> {
    Users findUsersById(Long id);
    Users findByUsername(String username);

//    @Query(value = "SELECT u.id,u.first_name,u.password,u.patronymic,u.photo_name,u.surname,u.username FROM users u INNER JOIN orders o ON u.id = o.customer_id OR u.id = o.manager_id where u.id = ?1",nativeQuery = true)
//    Iterable<Users> findOrdersById(Long id);

}
