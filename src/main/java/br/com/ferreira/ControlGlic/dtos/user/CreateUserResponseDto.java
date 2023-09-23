package br.com.ferreira.ControlGlic.dtos.user;

import br.com.ferreira.ControlGlic.entities.User;

import java.util.Date;

public record CreateUserResponseDto(
        String id,
        String name,
        String email,
        Date birthDate
) {
    public CreateUserResponseDto(User user) {
        this(user.getUuid(), user.getName(), user.getEmail(), user.getBirthDate());
    }
}
