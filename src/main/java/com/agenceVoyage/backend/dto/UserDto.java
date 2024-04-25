package com.agenceVoyage.backend.dto;


import com.agenceVoyage.backend.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String firstName;

    private String lastName;

    private String username;


    public static User toEntity(UserDto userDto){
        User user = new User();
        return User.builder()
                .firstName(userDto.firstName)
                .lastName(userDto.lastName)
                .username(userDto.lastName)
                .build();

    }

    public static UserDto toDto(User user){
        UserDto userDto = new UserDto();
        return builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getFirstName())
                .build();
    }

}
