package com.zizimee.api.pimanager.request.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ResponseRepository extends JpaRepository<Response, Long> {
}