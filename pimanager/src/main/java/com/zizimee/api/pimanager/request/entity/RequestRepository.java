package com.zizimee.api.pimanager.request.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long>{
    @Query("SELECT p FROM Request p ORDER BY p.id DESC")
    List<Request> findAllDesc();
}