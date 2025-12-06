package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;

@Component
class UserMapper {

    public User toEntity(final UserDto dto) {
        return User.builder()
                .id(dto.id())
                .email(dto.email())
                .birthdate(dto.birthdate())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .build();
    }

    public UserDto toDto(final User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthdate(user.getBirthdate())
                .build();
    }

    public UserDto toSimpleDto(final User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
