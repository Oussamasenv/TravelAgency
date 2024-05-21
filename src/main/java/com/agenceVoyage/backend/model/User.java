package com.agenceVoyage.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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
    private long id;

    @Column(name = "first_name")
    @Size(min = 5, max = 20)
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 5, max = 20)
    private String lastName;

    @Column(name = "username", unique = true)
    @Size(min = 5, max = 20)
    private String username;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NumberFormat
    @Column(length = 10, unique = true)
    private String phoneNumber;

    @NotBlank
    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user")
    private Reservation reservation;


    @OneToMany(mappedBy = "user")
    @JsonIgnore
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
