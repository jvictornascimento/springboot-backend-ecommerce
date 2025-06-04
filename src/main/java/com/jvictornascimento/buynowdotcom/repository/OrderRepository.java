package com.jvictornascimento.buynowdotcom.repository;

import com.jvictornascimento.buynowdotcom.model.Order;
import com.jvictornascimento.buynowdotcom.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
