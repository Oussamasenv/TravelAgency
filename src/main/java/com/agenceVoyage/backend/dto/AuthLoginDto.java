package com.agenceVoyage.backend.dto;
import jakarta.validation.constraints.NotBlank;

public class AuthLoginDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
