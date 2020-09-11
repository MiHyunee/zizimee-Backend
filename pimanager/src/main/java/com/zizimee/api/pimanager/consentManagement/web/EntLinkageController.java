package com.zizimee.api.pimanager.consentManagement.web;

import com.zizimee.api.pimanager.consentManagement.dto.RequestLinkageDto;
import com.zizimee.api.pimanager.consentManagement.service.EntLinkageService;
import com.zizimee.api.pimanager.enterprise.dto.ResponseEnterpriseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RequestMapping("/linkage")
@RestController
public class EntLinkageController {

    private final EntLinkageService entLinkageService;

    @PostMapping("")
    public ResponseEntity interlock(HttpServletRequest request, @RequestBody RequestLinkageDto linkageDto) {
        ResponseEnterpriseDto dto = entLinkageService.save(linkageDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
