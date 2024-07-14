package com.microservicesproject.order_service.Repository;

import com.microservicesproject.order_service.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {


}
