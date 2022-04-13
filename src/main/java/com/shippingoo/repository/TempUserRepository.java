package com.shippingoo.repository;


import java.util.Optional;

import com.shippingoo.entity.TempoUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempUserRepository extends JpaRepository<TempoUser, Long> {

    Optional<TempoUser> findByEmail(String email);

    Optional<TempoUser> findByToken(String token);

    Optional<TempoUser> findByUsername(String username);

    void deleteByToken(String token);

    Long countByEmail(String email);

    Long countByUsername(String username);



}
