package com.zizimee.api.pimanager.common.auth;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoder {

    public String genSalt() {
        return BCrypt.gensalt();
    }

    public String encodePassword(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }
}