package br.com.ferreira.ControlGlic.dtos.user;

public record CreateUserRequestDto(
        String name,
        String email,
        String birthDate,
        String password
) {
}
