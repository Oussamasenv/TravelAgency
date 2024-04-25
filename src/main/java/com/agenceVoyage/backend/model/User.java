package com.agenceVoyage.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Double id;

    @NotBlank
    @Column(name = "first_name")
    @Size(min = 5, max = 20)
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    @Size(min = 5, max = 20)
    private String lastName;

    @NotBlank
    @Column(name = "username", unique = true)
    @Size(min = 5, max = 20)
    private String username;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    @NumberFormat
    @Column(length = 10, unique = true)
    private String phoneNumber;

    @Size(min = 8, max = 20)
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Collection<Reservation> reservations;


    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<Token> tokens;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

}
