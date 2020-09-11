package com.zizimee.api.pimanager.consentManagement.service;

import com.zizimee.api.pimanager.common.util.LinkageWebDriver;
import com.zizimee.api.pimanager.consentManagement.dto.RequestLinkageDto;
import com.zizimee.api.pimanager.consentManagement.dto.ResponseEntLinkageDto;
import com.zizimee.api.pimanager.consentManagement.entity.EntLinkage;
import com.zizimee.api.pimanager.consentManagement.entity.EntLinkageRepository;
import com.zizimee.api.pimanager.enterprise.dto.ResponseEnterpriseDto;
import com.zizimee.api.pimanager.enterprise.entity.Enterprise;
import com.zizimee.api.pimanager.enterprise.entity.EnterpriseRepository;
import com.zizimee.api.pimanager.user.entity.User;
import com.zizimee.api.pimanager.user.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EntLinkageService {

    private final EntLinkageRepository entLinkageRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final UserRepository userRepository;

    public ResponseEntity save(RequestLinkageDto linkageDto) {
        String id = linkageDto.getEntId();
        String password = linkageDto.getEntPw();

        LinkageWebDriver loginTest = new LinkageWebDriver();
        if(loginTest.login(id, password)) {
            Enterprise enterprise = enterpriseRepository.findByName(linkageDto.getEntName());
            User user = userRepository.findByName(linkageDto.getUserName());
            EntLinkage entLinkage = EntLinkage.builder()
                    .enterprise(enterprise)
                    .user(user)
                    .build();
            entLinkageRepository.save(entLinkage);

            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("연동 불가");
        }
    }

}
