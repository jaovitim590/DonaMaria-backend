package com.donaMaria_.demo.repositories;

import com.donaMaria_.demo.models.Order;
import com.donaMaria_.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
