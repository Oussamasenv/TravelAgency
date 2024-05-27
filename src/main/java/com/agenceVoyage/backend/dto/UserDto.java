package com.agenceVoyage.backend.dto;

import com.agenceVoyage.backend.model.Reservation;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import java.util.Collection;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private long id;

    @Column(name = "first_name")
    @Size(min = 5, max = 20)
    @NotBlank
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 5, max = 20)
    @NotBlank
    private String lastName;

    @Column(name = "username", unique = true)
    @Size(min = 5, max = 20)
    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @NumberFormat
    @Column(length = 10, unique = true)
    private String phoneNumber;

    @NotBlank
    private String password;

    private Collection<Reservation> reservations;

}
