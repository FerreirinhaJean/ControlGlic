package br.com.ferreira.ControlGlic.dtos;

public record UpdateUserRequestDto(
        String name,
        String email,
        String birthDate
) {
}
