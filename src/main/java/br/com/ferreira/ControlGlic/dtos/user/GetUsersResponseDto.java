package br.com.ferreira.ControlGlic.dtos.user;

public record GetUsersResponseDto(
        String id,
        String name,
        String email,
        String birthDate
) {
    public GetUsersResponseDto(String id, String name, String email, String birthDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
    }
}
