package com.jvictornascimento.buynowdotcom.repository;

import com.jvictornascimento.buynowdotcom.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoy extends JpaRepository<User, Long> {
    boolean existsByEmail(String userEmail);
}
