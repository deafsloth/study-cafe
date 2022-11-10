package com.jdong.studycafe.orders.repository;

import com.jdong.studycafe.orders.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
