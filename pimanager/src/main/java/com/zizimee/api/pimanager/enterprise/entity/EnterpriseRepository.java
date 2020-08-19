package com.zizimee.api.pimanager.enterprise.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    List<Enterprise> findByRegisterNmb(String registerNmb);
    Optional<Enterprise> findBySignUpId(String signUpId);
}
