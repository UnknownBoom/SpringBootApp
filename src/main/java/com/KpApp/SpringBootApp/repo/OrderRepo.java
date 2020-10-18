package com.KpApp.SpringBootApp.repo;

import com.KpApp.SpringBootApp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepo extends JpaRepository<Order,Long> {
    Order findById(long id);
    @Query(value = "SELECT o.id,o.order_date,o.order_name,o.order_scheme_name,o.planed_date_end,o.price,o.customer_id,o.manager_id,o.product_type FROM users u INNER JOIN orders o ON u.id = o.customer_id OR u.id = o.manager_id where u.id = ?1",nativeQuery = true)
    Iterable<Order> findOrdersByUser_idList(Long id);
    @Query(value = "select  * from orders where order_name = ?1",nativeQuery = true)
    Order findByOrder_name(String order_name);
}
