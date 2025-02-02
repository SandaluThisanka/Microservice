package com.ecommerce.platform.Repository;

import com.ecommerce.platform.Model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {


}
