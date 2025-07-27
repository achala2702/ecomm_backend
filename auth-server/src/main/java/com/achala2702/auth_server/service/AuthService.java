package com.achala2702.auth_server.service;

import com.achala2702.auth_server.dto.UserLoginRequestDto;
import com.achala2702.auth_server.dto.UserLoginResponseDto;
import com.achala2702.auth_server.dto.UserRegisterRequestDto;
import com.achala2702.auth_server.exception.UserAlreadyExistsException;
import com.achala2702.auth_server.exception.UserNotFoundException;
import com.achala2702.auth_server.model.UserModel;
import com.achala2702.auth_server.repository.AuthRepository;
import com.achala2702.auth_server.util.JwtUtil;
import com.achala2702.auth_server.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;

    public String registerUser(UserRegisterRequestDto userRegisterDto) {
        //check whether user already in db
        if(authRepository.existsByEmail(userRegisterDto.email())){
            throw new UserAlreadyExistsException("Registration failed: Email is already associated with an existing account.");
        }
        //map uer req to model and save
        UserModel user = userMapper.mapToUserModel(userRegisterDto);
        authRepository.save(user);
        return "User registered successfully.";
    }

    public UserLoginResponseDto userLogin(UserLoginRequestDto userLoginRequestDto) {
        UserModel user = authRepository.findByEmail(userLoginRequestDto.email()).orElseThrow(()->new UserNotFoundException("No account found with the provided email address."));

        //generate token
        String token = jwtUtil.generateJwt(user.getEmail());

        return UserLoginResponseDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userAddress(user.getUserAddress())
                .token(token)
                .build();
    }
}
