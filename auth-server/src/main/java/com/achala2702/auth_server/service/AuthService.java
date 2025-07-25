package com.achala2702.auth_server.service;

import com.achala2702.auth_server.dto.UserRegisterDto;
import com.achala2702.auth_server.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public String registerUser(UserRegisterDto userRegisterDto) {
        //logic to check whether user already in db and save user;
        if(authRepository.existsByEmail(userRegisterDto.getEmail())){
            return "user found";
        }

        return "registration successful";
    }
}
