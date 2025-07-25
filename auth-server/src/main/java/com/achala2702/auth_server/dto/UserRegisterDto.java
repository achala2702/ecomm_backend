package com.achala2702.auth_server.dto;

import lombok.Data;

@Data
public class UserRegisterDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
