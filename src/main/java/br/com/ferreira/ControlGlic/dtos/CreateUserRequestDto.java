package br.com.ferreira.ControlGlic.dtos;

public record CreateUserRequestDto(
        String name,
        String email,
        String birthDate,
        String password
) {
}
