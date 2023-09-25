package br.com.ferreira.ControlGlic.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateUserRequestDto(
        @NotBlank(message = "name is required")
        String name,
        @NotBlank(message = "email is required")
        String email,
        @NotBlank(message = "birthDate is required")
        @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "birthDate format is not valid")
        String birthDate
) {
}
