package com.zizimee.api.pimanager.user.service;

import com.zizimee.api.pimanager.user.entity.User;
import com.zizimee.api.pimanager.user.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserSerivce implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) {
        Optional<User> user = userRepository.findById(Integer.valueOf(userId));

        //if(!user.getId().equals(Integer.valueOf(userId))) {
        //    throw new Exception("Invalid Request");
        //}

        return org.springframework.security.core.userdetails.User.builder()
                .username(userId)
                .password("")
                .build();
    }
}
