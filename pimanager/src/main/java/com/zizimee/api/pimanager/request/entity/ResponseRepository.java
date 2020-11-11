package com.zizimee.api.pimanager.request.entity;

import com.zizimee.api.pimanager.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;


public interface ResponseRepository extends JpaRepository<Response, Long> {
    List<Response> findByUserId(Long id);

}