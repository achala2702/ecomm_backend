package com.achala2702.auth_server.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class UserAddress {

    @NotBlank
    @Column(nullable = false)
    private String houseNo;
    @NotBlank
    @Column(nullable = false)
    private String street;
    @NotBlank
    @Column(nullable = false)
    private String city;
    @NotBlank
    @Column(nullable = false)
    private String zipCode;
}
