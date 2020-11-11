package com.zizimee.api.pimanager.request.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ResponseRepository extends JpaRepository<Response, Long> {

    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM Response r " +
                    "WHERE r.Id_Request = ?1 ")
    Optional<Response> findByIdRequest(Long id);
}