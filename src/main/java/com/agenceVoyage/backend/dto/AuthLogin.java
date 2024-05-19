package com.agenceVoyage.backend.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AuthLogin {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
