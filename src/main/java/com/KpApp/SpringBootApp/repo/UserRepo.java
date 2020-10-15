package com.KpApp.SpringBootApp.repo;


import com.KpApp.SpringBootApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    User findUsersById(Long id);
    User findByUsername(String username);

//    @Query(value = "SELECT u.id,u.first_name,u.password,u.patronymic,u.photo_name,u.surname,u.username FROM users u INNER JOIN orders o ON u.id = o.customer_id OR u.id = o.manager_id where u.id = ?1",nativeQuery = true)
//    Iterable<Users> findOrdersById(Long id);

}
