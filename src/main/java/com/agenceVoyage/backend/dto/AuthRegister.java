package com.agenceVoyage.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

@Getter
@Setter
public class AuthRegister {

    private long id;

    @Size(min = 5, max = 20)
    private String firstName;

    @Size(min = 5, max = 20)
    private String lastName;


    @Size(min = 5, max = 20)
    private String username;

    @Email
    @NotBlank
    private String email;

    @NumberFormat
    @Length(min = 10, max = 10)
    private String phoneNumber;

    @NotBlank
    private String password;



}