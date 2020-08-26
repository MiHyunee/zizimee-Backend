package com.zizimee.api.pimanager.request.entity;

import com.zizimee.api.pimanager.request.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ResponseRepository extends JpaRepository<Response, Long> {
    @Query("SELECT p FROM Response p ORDER BY p.id DESC")
    List<Response> findAllDesc();
}