package com.jvictornascimento.buynowdotcom.repository;

import com.jvictornascimento.buynowdotcom.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>  {

    Cart findByUserId(Long userId);
}
