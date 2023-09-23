package br.com.ferreira.ControlGlic.dtos.user;

public record UpdateUserRequestDto(
        String name,
        String email,
        String birthDate
) {
}
