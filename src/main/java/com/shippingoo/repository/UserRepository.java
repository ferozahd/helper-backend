package com.shippingoo.repository;

import java.util.Optional;

import com.shippingoo.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
    Long countByEmail(String email);

    Long countByUsername(String username);
}
